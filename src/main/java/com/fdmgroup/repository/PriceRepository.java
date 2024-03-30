package com.fdmgroup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.fdmgroup.model.Price;

/**
 * Repository of Price
 * 
 * @author Corwin.Yip
 * @version 0.0.1
 * @since 25/03/24
 * 
 */

@Repository

public interface PriceRepository extends JpaRepository<Price, Integer> {
	/**
	 * It persists the price
	 * @param price This is the Price object
	 */
	void persist(Price price);
	
	/**
	 * It finds the price by id
	 * @param id this is the ID
	 * @return it returns the price according to the price id
	 */
	Optional<Price> findById(int id);
	
	/**
	 * It finds all the price
	 * @return It returns a List of Price object
	 */
	List<Price> findAll();
}
