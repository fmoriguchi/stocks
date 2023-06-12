/**
 * 
 */
package com.fmoriguchi.stocks.cheap.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @author fmoriguchi
 *
 */
@Component
public final class StocksFilterComposite implements StocksFilter {
	
	private final List<StocksFilter> filters;
	
	public StocksFilterComposite() {

		this(new ArrayList<>());
	}
	
	public StocksFilterComposite(List<StocksFilter> filters) {

		this.filters = filters;
	}
	
	public StocksFilterComposite(StocksFilter ...filters) {
		
		this(List.of(filters));
	}

	@Override
	public Iterable<Stocks> filter(Iterable<Stocks> stocks) {

		var filtered = stocks;
			
		for (StocksFilter filter : filters) {
			
			filtered = filter.filter(filtered);
		}
		
		return filtered;
	}
	
	public StocksFilterComposite add(StocksFilter filter) {
		
		filters.add(filter);
		
		return this;
	}
}
