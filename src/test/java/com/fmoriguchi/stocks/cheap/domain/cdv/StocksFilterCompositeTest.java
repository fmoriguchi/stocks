package com.fmoriguchi.stocks.cheap.domain.cdv;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fmoriguchi.stocks.cheap.domain.Stocks;
import com.fmoriguchi.stocks.cheap.domain.StocksFilter;
import com.fmoriguchi.stocks.cheap.domain.StocksFilterComposite;

class StocksFilterCompositeTest {

	@Mock
	private StocksFilter one;
	
	@Mock
	private StocksFilter two;
	
	@Mock
	private StocksFilter three;
	
	@BeforeEach
	void start() {
		
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void checkIfCallAllFiltersTest() {

		// A
		var stocks = stocks();
		
		when(one.filter(stocks)).thenReturn(stocks);
		when(two.filter(stocks)).thenReturn(stocks);
		when(three.filter(stocks)).thenReturn(stocks);
		
		var composite = new StocksFilterComposite(one, two, three);
		
		// A
		var filtered = composite.filter(stocks());
		
		// A
		verify(one).filter(stocks);
		verify(two).filter(stocks);
		verify(three).filter(stocks);
		
		assertIterableEquals(stocks, filtered);
	}
	
	private Collection<Stocks> stocks() {

		var stocks = new ArrayList<Stocks>();
		stocks.add(new Stocks("KOFE4", new BigDecimal("20.97"), new BigDecimal("7.55"), new BigDecimal("2.16"), BigDecimal.ZERO, new BigDecimal("5891")));
		stocks.add(new Stocks("ACME4", new BigDecimal("15.55"), new BigDecimal("1.55"), new BigDecimal("12.16"), BigDecimal.ZERO, new BigDecimal("5891.375")));
		stocks.add(new Stocks("MKII4", new BigDecimal("13.96"), new BigDecimal("6.55"), new BigDecimal("13.16"), BigDecimal.ZERO, new BigDecimal("1858911.375")));
		stocks.add(new Stocks("DBGT3", new BigDecimal("60"), new BigDecimal("5.55"), new BigDecimal("16.1"), BigDecimal.ZERO, new BigDecimal("205891.375")));
		stocks.add(new Stocks("UMBR3", new BigDecimal("5.50"), new BigDecimal("4.56"), new BigDecimal("21.0"), BigDecimal.ZERO, new BigDecimal("891.375")));
		stocks.add(new Stocks("SFZU3", new BigDecimal("10"), new BigDecimal("10.55"), new BigDecimal("21.16"), BigDecimal.ZERO, new BigDecimal("5891375")));
		stocks.add(new Stocks("CPSC4", new BigDecimal("20.55"), new BigDecimal("2.55"), new BigDecimal("22.16"), BigDecimal.ZERO, new BigDecimal("158911.375")));

		return stocks;
	}	
}
