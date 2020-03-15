package com.microservices.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.product.dataaccessobject.BrandRepository;
import com.microservices.product.entity.Brand;

@Service
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	BrandRepository myBrandRepo;
	
	@Override
	public List<Brand> getAllBrands(){
		return myBrandRepo.findAllByOrderByName();
	}
	
}
