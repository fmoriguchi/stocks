/**
 * 
 */
package com.fmoriguchi.stocks.cheap.infrastructure;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_EVEN;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fmoriguchi.stocks.cheap.application.ReadStocks;
import com.fmoriguchi.stocks.cheap.domain.Stocks;

/**
 * @author fmoriguchi
 *
 */
@Component
public class XlsReadStocks implements ReadStocks {
	
	private static final Logger log = LoggerFactory.getLogger(XlsReadStocks.class);

	private static final BigDecimal ONE_HUNDRED = new BigDecimal("100");
	private static final int FIRST_SHEET = 0;
	private static final int NAME = 0;
	private static final int PRICE = 1;
	private static final int EBIT_MARGIN = 2;
	private static final int EV_EBIT = 3;
	private static final int DIVIDEND_YIELD = 4;
	private static final int EQUILITY = 5;

	@Override
	public Iterable<Stocks> read(InputStream stream) {
		
		var stocks = new ArrayList<Stocks>();
		
		try(var workbook = new XSSFWorkbook(stream)) {
			
			var rowIterator = workbook.getSheetAt(FIRST_SHEET).iterator();
			
			while(rowIterator.hasNext()) {
				
				var cellIterator = rowIterator.next().cellIterator();

				var stock = new StockStructure();
				
				while(cellIterator.hasNext()) {
					
					var cell = cellIterator.next();
					
					extractStockInfo(cell, stock);
				}
				
				addStock(stock, stocks);
			}
			
			return stocks;
			
		} catch (Exception e) {
			
			log.error("Cannot read xls stocks", e);
			
			return Collections.emptyList();
		}
	}

	private void addStock(StockStructure stock, List<Stocks> stocks) {
		
		try {
			
			stocks.add(stock.to());
			
		} catch (Exception e) {
		
			log.error("Cannot add stock {}. Reason: {}", stock.name, e.getMessage());
		}
	}

	private void extractStockInfo(Cell cell, StockStructure stock) {
		try {
			
			switch (cell.getColumnIndex()) {
				case NAME -> stock.name = cell.getStringCellValue();
				case PRICE -> stock.price = getNumericCellValue(cell, stock);
				case EBIT_MARGIN -> stock.ebitMargin = getPercentageCellValue(cell, stock);
				case EV_EBIT -> stock.evEbit = getNumericCellValue(cell, stock);
				case DIVIDEND_YIELD -> stock.dividendYield = getPercentageCellValue(cell, stock);
				case EQUILITY -> stock.equility = getNumericCellValue(cell, stock);
				default -> log.warn("Column '{}' is not mapped to read", cell.getColumnIndex());
			}
			
		} catch (Exception e) {
			
			cannotReadPosition(cell, stock, e);
		}
	}

	private static final class StockStructure {

		String name;
		BigDecimal price;
		BigDecimal ebitMargin;
		BigDecimal evEbit;
		BigDecimal dividendYield;
		BigDecimal equility;
		
		Stocks to() {

			return new Stocks(name, 
							  treat(price), 
							  treat(ebitMargin), 
							  treat(evEbit), 
							  treat(dividendYield), 
							  treat(equility));
		}
		
		private BigDecimal treat(BigDecimal value) {
			
			return value != null ? value.setScale(2, HALF_EVEN) : ZERO;
		}
	}
	
	private BigDecimal getNumericCellValue(Cell cell, StockStructure stock) {
		
		try {
			
			return BigDecimal.valueOf(cell.getNumericCellValue());
			
		} catch (Exception e) {

			cannotReadPosition(cell, stock, e);
			return ZERO;
		}
		
	}
	
	private BigDecimal getPercentageCellValue(Cell cell, StockStructure stock) {
		
		if (cell.getCellStyle().getDataFormatString().contains("%")) {

			return getNumericCellValue(cell, stock)
						.multiply(ONE_HUNDRED);
		}
		
		if (CellType.STRING.equals(cell.getCellType())) {
			return new BigDecimal(cell.getStringCellValue().replace("%", "").replace(",", "."));
		}

		return getNumericCellValue(cell, stock);
	}
	
	private void cannotReadPosition(Cell cell, StockStructure stock, Exception e) {

		log.error("Cannot read position C:{}/R:{} from stock {}. Problem > {}", cell.getColumnIndex(), cell.getRowIndex(), stock.name, e.getMessage());
	}
}
