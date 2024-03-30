package com.fdmgroup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.model.Item;

/**
 * Repository of Item
 * 
 * @author Corwin.Yip
 * @version 0.0.1
 * @since 25/03/24
 * 
 */

@Repository

public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	/**
	 * It persists the item
	 * @param item This is the Item object
	 */
	void persist(Item item);

	/**
	 * It finds the item by id
	 * @param id This is Item id
	 * @return return Item if it exists
	 */
	Optional<Item> findByItemId(int id);

	/**
	 * It deletes the item by id
	 * @param id This is Item id This method delete the Item according to the id
	 */
	void deleteByItemId(int id);

	/**
	 * It finds all the item
	 * @return It returns all the item in a List
	 */
	List<Item> findAll();
}
