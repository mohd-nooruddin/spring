package com.spring.security.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.spring.security.entity.Products;

import jakarta.annotation.PostConstruct;

@Service
public class ProductService {
	
	List<Products> productList = null;
	
	@PostConstruct
	public void loadProductData() {
		Random random = new Random();
		
	    productList = IntStream.rangeClosed(2161, 2261)
	            .mapToObj(i -> new Products(
	                     i,
	                    "Product " + i,
	                    random.nextInt(20),
	                    10.0 + random.nextDouble() * 4990.0)) // Random product price between 10.0 and 5000.0
	            .collect(Collectors.toList());
	}
	
	public List<Products> getProducts() {
		return productList;
	}
	
	public Products getProduct(int id) {
		return productList.stream()
				.filter(product -> product.getProductId() == id)
				.findAny()
				.orElseThrow(()-> new RuntimeException("Product "+id+" not found"));
	}
//	List<Products> productList = null;
//	
//	
//	public void loadProductData() {
//		productList = 
//				IntStream.rangeClosed(2161, 2261)
//				.mapToObj(i -> Products.builder()
//						.productId(i)
//						.productName("Product "+i)
//						.productQuantity(new Random().nextInt(20))
//						.productPrice(new Random().nextDouble(5000.00))
//						.build())
//				.collect(Collectors.toList());
//	}
	


}
