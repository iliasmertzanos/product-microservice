package com.microservices.product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import com.google.common.collect.Comparators;
import com.microservices.product.ProductApplication;
import com.microservices.product.datatranferobject.ProductDTOTest;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication.class,webEnvironment=WebEnvironment.RANDOM_PORT)
public class ControllerLayerTester {
	
	private static Log myLogger = LogFactory.getLog(ControllerLayerTester.class);
	
    private static RestTemplate restTemplate;
	
	private static HttpHeaders headers;
	
	@LocalServerPort
    private int port;
	
	@BeforeClass
	public static void runBeforeAllTestMethods() {
	 
	    restTemplate = new RestTemplate();
	    headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	}
	
	/**
     * simple test to check if product controller throws exception in case product doesn't exist
     */
	@Test
	public void testExceptionThrownNonExistingProduct() {
		
		try {
			restTemplate.getForEntity("http://localhost:"+port+"/v2/search/get/product/666666/",ProductDTOTest.class);
		}catch(HttpClientErrorException  e){
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
		}	
	}
	
	
	/**
     * simple test to verify the given requirements
     */
	@Test
	public void testBrandListIsOrdered() {
		
		TreeMap<String, List<LinkedHashMap>> productList=restTemplate.getForObject("http://localhost:"+port+"/v1/search/get/products/",TreeMap.class);
		
		//Check if brands are sorted by name
		assertTrue(Comparators.<String>isInOrder( new ArrayList<String>(productList.keySet()), Comparator.<String>naturalOrder()));
		
		for (List<LinkedHashMap> myProductList:productList.values()) {
			
			//Check if property brand is omitted  on products
			myProductList.forEach(product->assertTrue(!product.containsKey("brand")));
			
			//Check if Products are sorted by price 
			assertTrue(Comparators.<LinkedHashMap>isInOrder( myProductList, new Comparator<LinkedHashMap>() {				
				//
				@Override
				public int compare(LinkedHashMap o1, LinkedHashMap o2) {
					return ((Double)o1.get("price")).compareTo(((Double)o2.get("price")));
				}
				
			}));
			
			//Check if Products have the property event in case property onSale is true
			for(LinkedHashMap product:myProductList){
				assertTrue(!product.containsKey("brand"));
				ResponseEntity<ProductDTOTest> myProduct=restTemplate.getForEntity("http://localhost:"+port+"/v2/search/get/product/"+product.get("id")+"/",ProductDTOTest.class);
				if(myProduct.getBody().getOnSale()) {
					assertTrue(product.containsKey("event") && "ON SALE".equals(product.get("event")));
				}else {
					assertTrue(!product.containsKey("event"));
				}			
			}			
		}
	}

}
