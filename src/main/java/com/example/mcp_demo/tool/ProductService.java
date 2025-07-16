package com.example.mcp_demo.tool;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import com.example.mcp_demo.domain.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {
  
	
    @SuppressWarnings("unchecked")
	@Tool(name="getProducts",
            description="Get All products in database")
    public List<Product> getProducts() {;
        List<Product> products = new ArrayList<Product>();

        products.add(Product.builder().
        		id(1L).
        		productName("아이폰13미니").
        		description("아이폰 시리즈").
        		catalog("iphone").
        		price(10000000)
        		.build());
        
        products.add(Product.builder().
        		id(1L).
        		productName("아이폰13").
        		description("아이폰 시리즈").
        		catalog("iphone").
        		price(13000000)
        		.build());
        
        
        
        List<Product> prods = httpClient();
        
        for(Product product : prods) {
        	products.add(product);
        }
        
        return products;
    }
    
    @Tool(name = "findByProductName",
    		description = "Find a product by productName"
    		)
    public Product findByProductName(String productName) {
    	HttpClient client = HttpClient.newHttpClient();
    	
        // Create a HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8060/product/"+productName))
                .GET()
                .build();
        
        Product product = null;
        
        try {
        	Gson gson = new Gson();
        	
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonStr = response.body();
            
        	product = gson.fromJson(jsonStr, Product.class);
        	
        }  catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }        
        
        return product;
    }

    public List<Product> httpClient() {
        // Create an HttpClient instance
        HttpClient client = HttpClient.newHttpClient();
        
        // Create a HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8060/products"))
                .GET()
                .build();
        
        List<Product> products = null;
        
        try {
        	Gson gson = new Gson();
            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonStr = response.body();
            
            Type listOfMyClassObject = new TypeToken<ArrayList<Product>>() {}.getType();
            products = gson.fromJson(jsonStr, listOfMyClassObject);
            
            // Print the response status code and body
            log.info("Status code: " + response.statusCode());
            log.info("Response body: " + response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }        
        return products;
    }
}
