/**
 * 
 */
package com.fmoriguchi.stocks.cheap.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

/**
 * @author fmoriguchi
 *
 */
class StocksTest {

	@Test
	void checkIfCompanyIdHasNoNumberTest() {
		
		var stock = new Stocks("DBGT4", new BigDecimal("60"), 5.55, 16.1, 0.0, new BigDecimal("105891.375"));
		
		assertEquals("DBGT", stock.companyID());
	}
}
