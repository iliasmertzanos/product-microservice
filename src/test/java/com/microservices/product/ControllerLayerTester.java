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
import com.microservices.product.datatranferobject.ProductDTO;
import com.microservices.product.datatranferobject.ProductDTOTest;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
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
		
		ResponseEntity<TreeMap<String, List<ProductDTO>>> productListResponse=restTemplate.exchange(
			    "http://localhost:"+port+"/v1/search/get/products/",
			    HttpMethod.GET,
			    new HttpEntity<>(null, headers),
			    new ParameterizedTypeReference<TreeMap<String, List<ProductDTO>>>() {}
			);
		
		TreeMap<String, List<ProductDTO>> productList=productListResponse.getBody();
		
		//Check if brands are sorted by name
		assertTrue(Comparators.<String>isInOrder( new ArrayList<String>(productList.keySet()), Comparator.<String>naturalOrder()));
		
		for (List<ProductDTO> myProductList:productList.values()) {
			
//			//Check if property brand is omitted  on products
//			myProductList.forEach(product->assertTrue(!product.containsKey("brand")));
			
			//Check if Products are sorted by price 
			assertTrue(Comparators.<ProductDTO>isInOrder( myProductList, new Comparator<ProductDTO>() {				
				//
				@Override
				public int compare(ProductDTO o1, ProductDTO o2) {
					return o1.getPrice().compareTo(o2.getPrice());
				}
				
			}));
			
			//Check if Products have the property event in case property onSale is true
			for(ProductDTO product:myProductList){
				ResponseEntity<ProductDTOTest> myProduct=restTemplate.getForEntity("http://localhost:"+port+"/v2/search/get/product/"+product.getId()+"/",ProductDTOTest.class);
				if(myProduct.getBody().getOnSale()) {
					assertTrue("ON SALE".equals(product.getEvent()));
				}else {
					assertTrue(product.getEvent()==null);
				}			
			}			
		}
	}

}
