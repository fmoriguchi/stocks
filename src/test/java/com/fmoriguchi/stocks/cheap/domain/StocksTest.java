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
		
		var stock = new Stocks("DBGT4", new BigDecimal("60"), new BigDecimal("5.55"), new BigDecimal("16.1"), new BigDecimal("0.0"), new BigDecimal("105891.375"));
		
		assertEquals("DBGT", stock.companyID());
	}
}
