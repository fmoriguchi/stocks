/**
 * 
 */
package com.fmoriguchi.stocks.cheap.domain.cdv;

import static java.util.stream.StreamSupport.stream;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fmoriguchi.stocks.cheap.domain.Stocks;

/**
 * @author fmoriguchi
 *
 */
class DuplicateStocksTest {
	
	private static final Logger log = LoggerFactory.getLogger(DuplicateStocks.class);

	@Test
	void test() {
		
		var filter = new DuplicateStocks();
		
		var stocks = filter.filter(stocks());
		
		stocks.forEach(o -> log.info(o.toString()));
		
		assertIterableEquals(
						List.of("ACME4", "KOFE3", "MKII4", "DBGT3", "UMBR3", "CPSC3", "SFZU3"), 
						stream(stocks.spliterator(), false).map(Stocks::name).toList());
	}

	private Collection<Stocks> stocks() {

		var stocks = new ArrayList<Stocks>();
		stocks.add(new Stocks("DBGT4", new BigDecimal("60"), 5.55, 16.1, 0.0, new BigDecimal("105891.375")));
		stocks.add(new Stocks("KOFE4", new BigDecimal("20.97"), 7.55, 2.16, 0.0, new BigDecimal("5891")));
		stocks.add(new Stocks("ACME4", new BigDecimal("15.55"), 1.55, 12.16, 0.0, new BigDecimal("5891.375")));
		stocks.add(new Stocks("KOFE3", new BigDecimal("20.97"), 7.55, 2.16, 0.0, new BigDecimal("5892")));
		stocks.add(new Stocks("MKII4", new BigDecimal("13.96"), 6.55, 13.16, 0.0, new BigDecimal("1858911.375")));
		stocks.add(new Stocks("DBGT3", new BigDecimal("60"), 5.55, 16.1, 0.0, new BigDecimal("205891.375")));
		stocks.add(new Stocks("CPSC11", new BigDecimal("20.55"), 2.55, 22.16, 0.0, new BigDecimal("158911.375")));
		stocks.add(new Stocks("UMBR3", new BigDecimal("5.50"), 4.56, 21.0, 0.0, new BigDecimal("891.375")));
		stocks.add(new Stocks("CPSC3", new BigDecimal("20.55"), 2.55, 22.16, 0.0, new BigDecimal("258911.375")));
		stocks.add(new Stocks("SFZU3", new BigDecimal("10"), 10.55, 21.16, 0.0, new BigDecimal("5891375")));
		stocks.add(new Stocks("CPSC4", new BigDecimal("20.55"), 2.55, 22.16, 0.0, new BigDecimal("158911.375")));

		return stocks;
	}	
}
