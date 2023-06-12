package com.fmoriguchi.stocks.cheap.domain.cdv;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import com.fmoriguchi.stocks.cheap.domain.Stocks;

class StocksTestRepository {
	
	private StocksTestRepository() {}

	public static final Collection<Stocks> stocks() {
		
		Stocks PRNR3 = new Stocks("PRNR3", new BigDecimal("8.57"), 12.75, 3.49, 3.01, new BigDecimal("1723.787"));
		Stocks goau4 = new Stocks("GOAU4", new BigDecimal("11.70"), 20.96, 1.27, 10.77, new BigDecimal("91385.575"));
		Stocks wizc3 = new Stocks("WIZC3", new BigDecimal("5.92"), 36.42, 3.51, 7.52, new BigDecimal("3686.683"));
		Stocks petr4 = new Stocks("PETR4", new BigDecimal("27.18"), 45.26, 2.07, 47.65, new BigDecimal("1479148.959"));
		Stocks meal3 = new Stocks("MEAL3", new BigDecimal("2.2"), 11.55, 3.62, 0.0, new BigDecimal("1815.120"));
		Stocks AALR3 = new Stocks("AALR3", new BigDecimal("23.25"), -1.55, -212.16, 0.00, new BigDecimal("5891.375"));
		Stocks rani3 = new Stocks("RANI3", new BigDecimal("8.61"), 33.25, 6.18, 8.70, new BigDecimal("8530.957"));
		Stocks unip6 = new Stocks("UNIP6", new BigDecimal("69.72"), 29.41, 3.67, 37.78, new BigDecimal("19437.760"));
		Stocks tasa4 = new Stocks("TASA4", new BigDecimal("15.11"), 25.18, 3.74, 8.57, new BigDecimal("10850.579"));
		Stocks ALPA4 = new Stocks("ALPA4", new BigDecimal("11.03"), -3.22, -63.30, 0.00, new BigDecimal("5891.375"));
		
		return List.of(goau4, wizc3, petr4, meal3, unip6, tasa4, rani3, PRNR3, ALPA4, AALR3);
	}
}
