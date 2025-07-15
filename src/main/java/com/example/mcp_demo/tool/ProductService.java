package com.example.mcp_demo.tool;

import com.example.mcp_demo.domain.Product;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<Product>();
    @Tool(name="getProducts",
            description="Get All products in database")
    public List<Product> getProducts() {;
        products.add(new Product("아이폰13미니", "아이폰 시리즈입니다.", 2500000));
        products.add(new Product("아이폰13", "아이폰 시리즈입니다.", 3000000));

        return products;
    }

}
