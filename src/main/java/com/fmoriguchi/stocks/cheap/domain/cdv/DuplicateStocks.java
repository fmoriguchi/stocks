/**
 * 
 */
package com.fmoriguchi.stocks.cheap.domain.cdv;

import static java.util.stream.StreamSupport.stream;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fmoriguchi.stocks.cheap.domain.Stocks;
import com.fmoriguchi.stocks.cheap.domain.StocksFilter;

/**
 * @author fmoriguchi
 *
 */
class DuplicateStocks implements StocksFilter {

	private static final Logger log = LoggerFactory.getLogger(DuplicateStocks.class);
	
	@Override
	public Iterable<Stocks> filter(Iterable<Stocks> stocks) {
		
		var greaterLiquidity = new HashMap<String, Stocks>();
		
		stocks.forEach(stock -> {
			
			var companyId = stock.companyID();
			
			var stockFounded = greaterLiquidity.getOrDefault(companyId, stock);
			
			var betterStock = greaterliquidityCheck(stockFounded, stock);
			
			greaterLiquidity.put(companyId, betterStock);
		});
		
		greaterLiquidity.values().forEach(o -> log.info(o.name()));
		
		return stream(stocks.spliterator(), false)
				.filter(stock -> isBetterStock(stock, greaterLiquidity))
				.toList();
	}
	
	private boolean isBetterStock(Stocks stock, Map<String, Stocks> greaterLiquidity) {
		
		return greaterLiquidity.get(stock.companyID()).name().equals(stock.name());
	}
	
	private Stocks greaterliquidityCheck(Stocks one, Stocks two) {
		
		return one.liquidity().compareTo(two.liquidity()) > 0 ? one : two;
	}
}
