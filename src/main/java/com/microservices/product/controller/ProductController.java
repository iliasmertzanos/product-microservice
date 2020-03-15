package com.microservices.product.controller;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.product.datatranferobject.ProductDTOTest;
import com.microservices.product.entity.Product;
import com.microservices.product.exception.EntityNotFoundException;
import com.microservices.product.service.ProductService;

/**
 * A Product Controller with a single end point giving back a product retrieved from the data base according t a given Id.<br>
 * The standard java library class org.modelmapper.ModelMapper is being used to customly map Product beans to ProductDTOTests. 
 */
@RestController
@RequestMapping("v2/search")
public class ProductController {
	
	@Autowired
	ProductService myProductService;
	
	@GetMapping("/get/product/{id}")
	public ProductDTOTest getProductById(@PathVariable Long id) throws EntityNotFoundException {
		ModelMapper myMapper=new ModelMapper();
		Product myProduct=myProductService.getProductById(id);
		
		return myMapper.map(myProduct, ProductDTOTest.class);
		
	}

}
