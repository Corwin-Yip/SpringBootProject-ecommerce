package com.fdmgroup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.fdmgroup.model.Order;
import com.fdmgroup.model.User;

/**
 * Repository of Order
 * 
 * @author Corwin.Yip
 * @version 0.0.1
 * @since 25/03/24
 * 
 */

@Repository

public interface OrderRepository extends JpaRepository<Order, Integer> {
	/**
	 * It persists the order
	 * @param order this is the order object
	 */
	void persist(Order order);
	
	/**
	 * It finds the order by id
	 * @param id This is the Order id
	 * @return return Order according to the ID
	 */
	Optional<Order> findByOrderId(int id);
	
	/**
	 * It finds order by inputed User
	 * @param user this is the User object
	 * @return It returns order according to the User
	 */
	Optional<List<Order>> findByUser(User user);
	
		
	/**
	 * It deletes the order by id
	 * @param id this is the order id to be deleted
	 */
	void deleteByOrderId(int id);

}
