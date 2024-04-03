package com.fdmgroup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fdmgroup.model.Product;

/**
 * Repository of Product
 * 
 * @author Corwin.Yip
 * @version 0.0.1
 * @since 25/03/24
 * 
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	/**
	 * It finds the product by id
	 * 
	 * @param id This is the product Id
	 * @return It returns the product according to the input id
	 */
	Optional<Product> findById(int id);

	/**
	 * It finds the product by name
	 * 
	 * @param productName This is the product Name
	 * @return It returns the Product according to the input name
	 */
	Optional<Product> findByName(String name);

	/**
	 * It deletes the product by id
	 * 
	 * @param id This is the product Id to be deleted
	 */
	void deleteById(int id);

	/**
	 * It update the product
	 * 
	 * @param name        The product name
	 * @param description The new description
	 */
	@Modifying
	@Transactional
	@Query("UPDATE Product p SET p.description = :description WHERE p.name = :name")
	void updateProductDescription(@Param("name") String name, @Param("description") String description);

	/**
	 * It finds all the product
	 * 
	 * @return It returns all the product in a List
	 */
	List<Product> findAll();
}
