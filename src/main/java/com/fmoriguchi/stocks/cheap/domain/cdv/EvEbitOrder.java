/**
 * 
 */
package com.fmoriguchi.stocks.cheap.domain.cdv;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.StreamSupport;

import com.fmoriguchi.stocks.cheap.domain.Stocks;
import com.fmoriguchi.stocks.cheap.domain.StocksFilter;

/**
 * @author fmoriguchi
 *
 */
class EvEbitOrder implements StocksFilter {

	@Override
	public Iterable<Stocks> filter(Iterable<Stocks> stocks) {

		return StreamSupport.stream(stocks.spliterator(), false)
					 .filter(e -> Objects.nonNull(e.evEbit()))
					 .sorted(Comparator.comparing(Stocks::evEbit))
					 .toList();
	}
}
