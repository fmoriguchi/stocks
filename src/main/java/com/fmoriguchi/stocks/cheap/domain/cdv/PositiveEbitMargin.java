/**
 * 
 */
package com.fmoriguchi.stocks.cheap.domain.cdv;

import java.util.stream.StreamSupport;

import com.fmoriguchi.stocks.cheap.domain.Stocks;
import com.fmoriguchi.stocks.cheap.domain.StocksFilter;

/**
 * @author fmoriguchi
 *
 */
class PositiveEbitMargin implements StocksFilter {

	@Override
	public Iterable<Stocks> filter(Iterable<Stocks> stocks) {

		return StreamSupport
					  .stream(stocks.spliterator(), false)
					  .filter(this::positive)
					  .toList();
	}

	private boolean positive(Stocks stocks) {
		
		return stocks.ebitMargin() != null && stocks.ebitMargin() > 0;
	}
}
