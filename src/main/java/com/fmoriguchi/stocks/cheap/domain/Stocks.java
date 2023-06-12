package com.fmoriguchi.stocks.cheap.domain;

import java.math.BigDecimal;

/**
 * 
 * @author fmoriguchi
 *
 */
public record Stocks(String name, 
					 BigDecimal price, 
					 BigDecimal ebitMargin, 
					 BigDecimal evEbit, 
					 BigDecimal dividendYield,
					 BigDecimal liquidity) {
	
	public String companyID() {
		
		return name.replaceAll("\\d", "");
	}
}
