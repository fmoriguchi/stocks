/**
 * 
 */
package com.fmoriguchi.stocks.cheap.infrastructure;

import static java.math.RoundingMode.HALF_EVEN;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
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
			}
			
		} catch (Exception e) {
			log.error("Cannot read position {}:{} from stock {}. Problem > {}", cell.getAddress().getRow(), cell.getAddress().getColumn(), stock.name, e.getMessage());
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
							  price.setScale(2, HALF_EVEN), 
							  ebitMargin.setScale(2, HALF_EVEN), 
							  evEbit.setScale(2, HALF_EVEN), 
							  dividendYield.setScale(2, HALF_EVEN), 
							  equility.setScale(2, HALF_EVEN));
		}
	}
	
	private BigDecimal getNumericCellValue(Cell cell, StockStructure stock) {
		
		try {

			return BigDecimal.valueOf(cell.getNumericCellValue());
			
		}catch (Exception e) {

			log.error("Cannot read position {}:{} from stock {}. Problem > {}", cell.getAddress().getRow(), cell.getAddress().getColumn(), stock.name, e.getMessage());
			return BigDecimal.ZERO;
		}
		
	}
	
	private BigDecimal getPercentageCellValue(Cell cell, StockStructure stock) {
		
		if (cell.getCellStyle().getDataFormatString().contains("%")) {

			return getNumericCellValue(cell, stock)
						.multiply(ONE_HUNDRED);
		}
		
		return getNumericCellValue(cell, stock);
	}
}
