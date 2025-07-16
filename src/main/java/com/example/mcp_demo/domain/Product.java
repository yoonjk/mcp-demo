package com.example.mcp_demo.domain;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter

public class Product {

	private Long id;
	

    private String productName;
	

    private String description;
	

    private String catalog;
	
	

    private long price;
	

	private BigDecimal rate;

	@Builder
    public Product(Long id, String productName, String description, String catalog, long price, BigDecimal rate) {
		this.id = id;
		this.productName = productName;
		this.description = description;
		this.catalog = catalog;
		this.price = price;
		this.rate = rate;
	}
}
