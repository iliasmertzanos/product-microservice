package com.microservices.product.datatranferobject;

import java.util.List;

import com.microservices.product.entity.Product;


/**
 * A simple class being used on the web layer exposing data to classes that use the current microservice
 */
public class BrandDTO {
	private String name;
	
	private List<ProductDTO> productList;
	
	public BrandDTO() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductDTO> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductDTO> productList) {
		this.productList = productList;
	}
	
	
}
