package com.microservices.product.dataaccessobject;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.microservices.product.entity.Brand;
import com.microservices.product.entity.Product;

public interface BrandRepository extends JpaRepository<Brand, Long>{
	
	List<Brand> findAllByOrderByName();
}
