package com.spring.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.entity.Products;
import com.spring.security.service.ProductService;

@RestController()
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping(value = "/product/all")
	public List<Products> getAllProdutcs() {
		return productService.getProducts();
	}
	
	@GetMapping(value = "/product")
	public Products getProductDetails(@RequestParam("id") int id ) {
		return productService.getProduct(id);
	}
}
