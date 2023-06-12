package com.fmoriguchi.stocks.cheap.application;

import java.io.InputStream;

import com.fmoriguchi.stocks.cheap.domain.Stocks;

/**
 * 
 * @author fmoriguchi
 *
 */
public interface CheckCheapStocks {
	
	Iterable<Stocks> read(InputStream stream);
}
