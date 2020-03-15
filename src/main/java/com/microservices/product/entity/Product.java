package com.microservices.product.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * A Bean that maps data regarding Products.
 * Product Bean relates with Brand bean over a {@code @ManyToOne} relationship  
 *
 */
@Entity
public class Product {
	
	@Id
	@GeneratedValue
    private Long id;
	
	@Column
	@NotNull(message = "Product can not have empty name.")
    private String name;
	
	@Column
    private Double price;
	
	@Column
    private Boolean onSale=false;
	
	@ManyToOne(cascade= {CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH})
    @JoinColumn(name="brand_id", nullable=false)
	private Brand brand;
	
	public Product() {
		
	}

	public Product(Long id, @NotNull(message = "Product can not have empty name.") String name, Double price,
			Boolean onSale, Brand brand) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.onSale = onSale;
		this.brand = brand;
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

	public Boolean getOnSale() {
		return onSale;
	}

	public void setOnSale(Boolean onSale) {
		this.onSale = onSale;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", onSale=" + onSale + "]";
	}	
	
}
