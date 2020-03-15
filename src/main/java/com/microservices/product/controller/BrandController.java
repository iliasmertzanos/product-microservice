package com.microservices.product.controller;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.product.controller.mapping.ModelMapper;
import com.microservices.product.datatranferobject.BrandDTO;
import com.microservices.product.datatranferobject.ProductDTO;
import com.microservices.product.service.BrandService;

/**
 * A Brand Controller with a single end point giving back a Map with all Products grouped by Brands.<br>
 * The class controller.mapping.ModelMapper is being used to customly map Brand beans to BrandDTOs. 
 */
@RestController
@RequestMapping("v1/search")
public class BrandController {
	
	@Autowired
	BrandService myBrandService;
	
	@GetMapping("/get/products/")
	public Map<String, List<ProductDTO>> getAllProductsMap(){
		
		List<BrandDTO> myList= myBrandService.getAllBrands()
		.stream().map(brand->ModelMapper.makeBrandDTO(brand))
		.collect(Collectors.toList());		
		
		Map<String, List<ProductDTO>> myMap=new TreeMap<String, List<ProductDTO>> ();
		
		myList.forEach(brand->myMap.put(brand.getName(), brand.getProductList()));
		
		return myMap;
	}
	
}
