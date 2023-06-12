/**
 * 
 */
package com.fmoriguchi.stocks.cheap.domain.cdv;

import java.math.BigDecimal;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fmoriguchi.stocks.cheap.domain.Stocks;
import com.fmoriguchi.stocks.cheap.domain.StocksFilter;

/**
 * @author fmoriguchi
 *
 */
class Liquidity implements StocksFilter {

	private static final Logger log = LoggerFactory.getLogger(Liquidity.class);
	
	private final BigDecimal limit;
	
	Liquidity(BigDecimal limit) {

		this.limit = limit;
	}

	@Override
	public Iterable<Stocks> filter(Iterable<Stocks> stocks) {
		
		log.info("Check if Stocks has equility equals or more than '{}'", limit);
		
		return StreamSupport
					 .stream(stocks.spliterator(), false)
					 .filter(this::hasGoodEquility)
					 .toList();
	}

	private boolean hasGoodEquility(Stocks stock) {
		
		return stock.liquidity() != null && limit.compareTo(stock.liquidity()) <= 0;
	}
}
