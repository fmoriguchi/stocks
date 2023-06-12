/**
 * 
 */
package com.fmoriguchi.stocks.cheap.interfaces;

import java.util.Collections;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fmoriguchi.stocks.cheap.application.CheckCheapStocks;
import com.fmoriguchi.stocks.cheap.domain.Stocks;

/**
 * @author fmoriguchi
 *
 */
@RestController
@RequestMapping("/stocks/cheaps")
class CheapStocksResource {
	
	private static final Logger log = LoggerFactory.getLogger(CheapStocksResource.class);
	
	private final CheckCheapStocks cheapStocks;
	
	public CheapStocksResource(CheckCheapStocks cheapStocks) {
		this.cheapStocks = cheapStocks;
	}

	@PostMapping("/xls")
	Iterable<Stocks> check(@RequestParam MultipartFile xls) {
		
		try {

			return cheapStocks.read(xls.getInputStream());
			
		} catch (Exception e) {
			
			log.error("Deu ruim", e);

			return Collections.emptyList();
		}
	}
	
	@PostMapping("/xls/names")
	Iterable<String> checkNames(@RequestParam MultipartFile xls) {
		
		return StreamSupport.stream(check(xls)
				.spliterator(), false)
				.map(Stocks::name)
				.toList();
	}
	
	@GetMapping("/xls/info")
	String info() {
		
		return "Stock, Price, Ebit Margin(%), Ev/Ebit, Dividend Yield(%), Liquidity";
	}	
	
}
