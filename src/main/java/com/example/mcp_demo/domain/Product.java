package com.example.mcp_demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Product {
    private String productName;
    private String description;
    private long price;

    public Product() {}

    public Product(String productName, String description, long price) {
        this.productName = productName;
        this.description = description;
        this.price = price;
    }
}
