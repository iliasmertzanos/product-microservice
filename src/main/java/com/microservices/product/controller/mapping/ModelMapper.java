package com.microservices.product.controller.mapping;

import java.util.List;
import java.util.stream.Collectors;

import com.microservices.product.datatranferobject.BrandDTO;
import com.microservices.product.datatranferobject.ProductDTO;
import com.microservices.product.entity.Brand;

public class ModelMapper {
	public static BrandDTO makeBrandDTO(Brand myBrand)
    {
		BrandDTO myBrandDTO=new BrandDTO();
		
		org.modelmapper.ModelMapper myStandardMapper=new org.modelmapper.ModelMapper();
		
		List<ProductDTO> myProductsList=myBrand.getProductList()
				.stream()
				.map(product->myStandardMapper.map(product, ProductDTO.class))
				.collect(Collectors.toList());
		myBrandDTO.setProductList(myProductsList);
		
		myBrandDTO.setName(myBrand.getName());
		
        return myBrandDTO;
    }
}
