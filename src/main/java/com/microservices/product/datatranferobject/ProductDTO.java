package com.microservices.product.datatranferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * A simple class being used on the web layer exposing data to classes that use the current microservice.<br>
 * Not all field are exposed such as {@code onSale} that is used in order to determine the value of {@code event} property.<br>
 * Moreover the current dto ignores property with null value (for example event)
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
	
	private Long id;
	
    private String name;	
	
    private Double price;	
    
    @JsonIgnore // omit this property when exposing data over the API
    private Boolean onSale;
    
    private String event;
    
    public ProductDTO() {
    	
    }    

	public ProductDTO(Long id, String name, Double price, String event, Boolean onSale) {
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
		return onSale?"ON SALE":null;
	}

	public void setEvent(String event) {
		this.event = event;
	}    
	
}
