package com.microservices.product.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.product.dataaccessobject.ProductRepository;
import com.microservices.product.entity.Product;
import com.microservices.product.exception.EntityNotFoundException;

@Service
public class ProductServiceImpl implements ProductService{	
	
	@Autowired
	ProductRepository myProductRepo;
	
	@Override
	public Product getProductById(Long id) throws EntityNotFoundException {
		
		Optional<Product> myProduct=myProductRepo.findById(id);
		return myProduct.orElseThrow(()-> new EntityNotFoundException("Product with id: "+id+" does not exist."));
		
	}
}
