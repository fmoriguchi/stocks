package com.fmoriguchi.stocks.cheap.domain.cdv;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fmoriguchi.stocks.cheap.domain.Stocks;

class EvEbitOrderTest {
	
	private static final Logger log = LoggerFactory.getLogger(LiquidityTest.class);

	@Test
	void test() {
		
		var evEbitOrder = new EvEbitOrder();
		
		var stocks = evEbitOrder.filter(needToOrder());
		
		stocks.forEach(o -> log.info(o.toString()));

		assertIterableEquals(expected(), stocks);	
	}
	
	private Iterable<Stocks> expected() {
		
		var stocks = new ArrayList<Stocks>();
		stocks.add(new Stocks("KOFE4", new BigDecimal("20.97"), new BigDecimal("7.55"), new BigDecimal("2.16"), BigDecimal.ZERO, new BigDecimal("5891")));
		stocks.add(new Stocks("ACME4", new BigDecimal("15.55"), new BigDecimal("1.55"), new BigDecimal("12.16"), BigDecimal.ZERO, new BigDecimal("5891.375")));
		stocks.add(new Stocks("MKII4", new BigDecimal("13.96"), new BigDecimal("6.55"), new BigDecimal("13.16"), BigDecimal.ZERO, new BigDecimal("85891.375")));
		stocks.add(new Stocks("DBGT3", new BigDecimal("60"), new BigDecimal("5.55"), new BigDecimal("16.1"), BigDecimal.ZERO, new BigDecimal("205891.375")));
		stocks.add(new Stocks("UMBR3", new BigDecimal("5.50"), new BigDecimal("4.56"), new BigDecimal("21.0"), BigDecimal.ZERO, new BigDecimal("891.375")));
		stocks.add(new Stocks("SFZU3", new BigDecimal("10"), new BigDecimal("10.55"), new BigDecimal("21.16"), BigDecimal.ZERO, new BigDecimal("5891375")));
		
		return stocks;
	}
	
	private Iterable<Stocks> needToOrder() {
		
		var stocks = new ArrayList<Stocks>();
		stocks.add(new Stocks("MKII4", new BigDecimal("13.96"), new BigDecimal("6.55"), new BigDecimal("13.16"), BigDecimal.ZERO, new BigDecimal("85891.375")));
		stocks.add(new Stocks("ACME4", new BigDecimal("15.55"), new BigDecimal("1.55"), new BigDecimal("12.16"), BigDecimal.ZERO, new BigDecimal("5891.375")));
		stocks.add(new Stocks("UMBR3", new BigDecimal("5.50"), new BigDecimal("4.56"), new BigDecimal("21.0"), BigDecimal.ZERO, new BigDecimal("891.375")));
		stocks.add(new Stocks("DBGT3", new BigDecimal("60"), new BigDecimal("5.55"), new BigDecimal("16.1"), BigDecimal.ZERO, new BigDecimal("205891.375")));
		stocks.add(new Stocks("KOFE4", new BigDecimal("20.97"), new BigDecimal("7.55"), new BigDecimal("2.16"), BigDecimal.ZERO, new BigDecimal("5891")));
		stocks.add(new Stocks("CPSC4", new BigDecimal("20.55"), new BigDecimal("2.55"), null, BigDecimal.ZERO, new BigDecimal("15891.375")));
		stocks.add(new Stocks("SFZU3", new BigDecimal("10"), new BigDecimal("10.55"), new BigDecimal("21.16"), BigDecimal.ZERO, new BigDecimal("5891375")));
		
		return stocks;
	}
}
