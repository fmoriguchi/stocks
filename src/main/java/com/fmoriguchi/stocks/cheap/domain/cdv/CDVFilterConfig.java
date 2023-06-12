package com.fmoriguchi.stocks.cheap.domain.cdv;

import java.math.BigDecimal;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fmoriguchi.stocks.cheap.domain.StocksFilter;
import com.fmoriguchi.stocks.cheap.domain.StocksFilterComposite;

@Configuration
@ConfigurationProperties(prefix = "stocks.cheap.filter")
class CDVFilterConfig {
	
    private BigDecimal liquidity;
    
	public BigDecimal getLiquidity() {
		return liquidity;
	}
	
	public void setLiquidity(BigDecimal liquidity) {
		this.liquidity = liquidity;
	}

	@Bean
	@Primary
	StocksFilter stockFilter() {
		
		var composite = new StocksFilterComposite();
		
		composite.add(new Liquidity(getLiquidity()))
				 .add(new PositiveEbitMargin())
				 .add(new EvEbitOrder())
				 .add(new DuplicateStocks());
		
		return composite;
	}
}
