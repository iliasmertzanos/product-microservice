package com.microservices.product.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;

/**
 * A Bean that maps data regarding Brands
 * Brand Bean relates with Product bean over a {@code @OneToMany} relationship  
 *
 */
@Entity
public class Brand {
	
	@Id
	@GeneratedValue
    private Long id;
	
	@Column
	@NotNull(message = "Brand can not have empty name.")
    private String name;
	
	@Column
    private String description;
	
	@OneToMany(mappedBy="brand"
	, cascade= {CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH},
	fetch = FetchType.EAGER)	
	@OrderBy("price ASC") // order products by price
	private List<Product> productList;
	
	public Brand() {
		
	}

	public Brand(Long id, @NotNull(message = "Brand can not have empty name.") String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	@Override
	public String toString() {
		return "Brand [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
	

}
