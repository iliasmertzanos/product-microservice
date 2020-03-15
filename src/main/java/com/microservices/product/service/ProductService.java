package com.microservices.product.service;

import com.microservices.product.entity.Product;
import com.microservices.product.exception.EntityNotFoundException;

public interface ProductService {
	
	public Product getProductById(Long id) throws EntityNotFoundException;

}
