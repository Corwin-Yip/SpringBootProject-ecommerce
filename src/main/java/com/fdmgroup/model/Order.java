package com.fdmgroup.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Order class
 * 
 * @author Corwin.Yip
 * @version 0.0.1
 * @since 25/03/24
 * 
 */

@Entity
@Table(name = "`Order`") // Escape the table name
@NamedQueries({ @NamedQuery(name = "Order.findAll", query = "select o from Order o"),
		@NamedQuery(name = "Order.findByUser", query = "select o from Order o where o.user = :user") })
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ORDER_ID")
	private int id;

	@ManyToOne
	@JoinColumn(name = "FK_USER_ID")
	private User user;

	@OneToOne
	@JoinColumn(name = "FK_Item_ID")
	private Item item;

	@Column(name = "ORDER_DATETIME")
	private LocalDateTime datetime;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param user
	 * @param item
	 */
	public Order(User user, Item item) {
		super();
		this.user = user;
		this.item = item;
		setdatetime(LocalDateTime.now());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public LocalDateTime getdatetime() {
		return datetime;
	}

	public void setdatetime(LocalDateTime datetimeColumn) {
		this.datetime = datetimeColumn;
	}

}

