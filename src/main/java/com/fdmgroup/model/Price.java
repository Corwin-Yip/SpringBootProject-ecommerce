package com.fdmgroup.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;

/**
 * Price class
 * 
 * @author Corwin.Yip
 * @version 0.0.1
 * @since 25/03/24
 * 
 */

@Entity
@NamedQuery(name = "Price.findAll", query = "select p from Price p")
public class Price {

	@Id
	@ManyToOne
	@JoinColumn(name = "FK_PRODUCT_ID")
	private Product product;

	@Column(name = "PRICE")
	private double price;

	@Column(name = "DATE")
	private LocalDateTime datetime;

	public Price() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param productId
	 * @param price
	 */
	public Price(Product product, double price) {
		super();
		this.product = product;
		this.price = price;
		this.datetime = LocalDateTime.now();
	}

	public Product getProduct() {
		return product;
	}

	public void setProductId(Product product) {
		this.product = product;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}

}

