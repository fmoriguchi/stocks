package com.fmoriguchi.stocks.cheap.domain;

public interface StocksFilter {

	Iterable<Stocks> filter(Iterable<Stocks> stocks); 
}
