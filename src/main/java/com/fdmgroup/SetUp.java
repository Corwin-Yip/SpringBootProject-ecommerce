package com.fdmgroup;

import com.fdmgroup.model.Item;
import com.fdmgroup.model.Order;
import com.fdmgroup.model.Price;
import com.fdmgroup.model.Product;
import com.fdmgroup.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class SetUp {
	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("E-commerce_mySQL");
	static EntityManager em = emf.createEntityManager();
	
	
	public static void main(String[] args) {
		User user1 = new User("john.smith", "apple123", "John", "Smith", "HK");
		User user2 = new User("jane.doe", "banana456", "Jane", "Doe", "US");

		Product product1 = new Product("jordon", "this is a air jordon");
		Product product2 = new Product("nike", "this is a nike");

		Price price1 = new Price(product1, 676.5);
		Price price2 = new Price(product2, 779.5);

		Item item1 = new Item(product1);
		Item item2 = new Item(product1);

		Order order1 = new Order(user1, item1);
		Order order2 = new Order(user2, item2);
		
		em.persist(product1);
		
		System.out.println("done");

	}

}
