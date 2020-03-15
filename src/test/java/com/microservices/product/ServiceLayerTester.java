package com.microservices.product;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.microservices.product.entity.Brand;
import com.microservices.product.service.BrandService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceLayerTester {
	
	@Autowired
	BrandService myBrandService;
	
	/**
     * simple test to check if service layer working properly
     */
    @Test
    public void testGetAllBrands() {
    	
    	List<Brand> myBrandList=  myBrandService.getAllBrands();
    	
    	System.out.println(myBrandList);
    	
    	assertTrue(!myBrandList.isEmpty());
    	
    	
    }
    
    

}
