package com.fdmgroup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fdmgroup.model.User;

/**
 * Repository of User
 * 
 * @author Corwin.Yip
 * @version 0.0.1
 * @since 25/03/24
 * 
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	/**
	 * It finds the user by id
	 * 
	 * @param id This is the user id
	 * @return it returns the User according to the input id
	 */
	Optional<User> findById(int id);

	/**
	 * It finds the user by name
	 * 
	 * @param username This is the user name
	 * @return It returns the User according to the input name
	 */
	Optional<User> findByUsername(String username);

	/**
	 * It deletes the user by id
	 * 
	 * @param id this is the id
	 */
	void deleteById(int id);

	/**
	 * It update the user
	 * 
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param address
	 */
	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.firstName = :firstName, u.lastName = :lastName, u.address = :address WHERE u.username = :username and u.password = :password")
	void updateUserDetails(@Param("username") String username, @Param("password") String password,
			@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("address") String address);

	/**
	 * It finds all the user
	 * 
	 * @return It returns all the User in a list
	 */
	List<User> findAll();

}
