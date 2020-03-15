package com.microservices.product.dataaccessobject;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.microservices.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
