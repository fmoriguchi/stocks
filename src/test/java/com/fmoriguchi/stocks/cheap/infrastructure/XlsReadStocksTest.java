package com.fmoriguchi.stocks.cheap.infrastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author fmoriguchi
 *
 */
class XlsReadStocksTest {

	@Test
	void readXlsTest() throws Exception {
		
		try (var xls = XlsReadStocksTest.class.getResourceAsStream("stocks.xlsx")) {
			
			var xlsReadStocks = new XlsReadStocks();
			
			var stocks = xlsReadStocks.read(xls);
			
			assertEquals(471, stocks.spliterator().estimateSize());
		}
	}
}
