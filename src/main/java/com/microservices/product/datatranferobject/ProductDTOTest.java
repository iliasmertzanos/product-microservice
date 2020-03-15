package com.microservices.product.datatranferobject;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * A simple class being used in order to get all properties and test the validity of the manipulation of the field event.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTOTest {
	
	private Long id;
	
    private String name;	
	
    private Double price;	
    
    private Boolean onSale;
    
    private String event;
    
    public ProductDTOTest() {
    	
    }    

	public ProductDTOTest(Long id, String name, Double price, String event, Boolean onSale) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.event = event;
		this.onSale=onSale;
	}
    
	public Boolean getOnSale() {
		return onSale;
	}

	public void setOnSale(Boolean onSale) {
		this.onSale = onSale;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}    
	
}
