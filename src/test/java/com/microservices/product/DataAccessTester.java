package com.microservices.product;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.microservices.product.dataaccessobject.BrandRepository;
import com.microservices.product.entity.Brand;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataAccessTester {
	
	@Autowired
	BrandRepository myBrandRepo;
	
    /**
     * simple test to check if dao layer working properly
     */
    @Test
    public void testGetAllBrands() {
    	
    	List<Brand> myBrandList= myBrandRepo.findAllByOrderByName();
    	
    	assertTrue(!myBrandList.isEmpty());
    }
    
    

}
