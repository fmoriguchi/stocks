/**
 * 
 */
package com.fmoriguchi.stocks.cheap.application;

import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.fmoriguchi.stocks.cheap.domain.StocksFilter;
import com.fmoriguchi.stocks.cheap.domain.Stocks;

/**
 * @author fmoriguchi
 *
 */
@Component
public class CheckCheapStocksImpl implements CheckCheapStocks {
	
	private final ReadStocks repository;
	private final StocksFilter stocksFilter;

	public CheckCheapStocksImpl(ReadStocks read, StocksFilter filter) {

		this.repository = read;
		this.stocksFilter = filter;
	}

	@Override
	public Iterable<Stocks> read(InputStream stream) {

		var stocks = repository.read(stream);
		
		return stocksFilter.filter(stocks);
	}
}
