package com.fdmgroup.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

/**
 * Product class
 * 
 * @author Corwin.Yip
 * @version 0.0.1
 * @since 25/03/24
 * 
 */

@Entity
@NamedQueries({ @NamedQuery(name = "Product.findAll", query = "select p from Product p"),
		@NamedQuery(name = "Product.findByName", query = "select p from Product p where p.name = :name") })
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRODUCT_ID")
	private int id;

	private String name;

	private String description;

	public Product() {
		super();
	}

	/**
	 * @param name
	 * @param description
	 */
	public Product(String name, String description) {
		super();
		this.name = name;
		this.description = description;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public void updateDetails(Product updatedProduct) {
		setName(updatedProduct.getName());
		setDescription(updatedProduct.getDescription());
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

}
