package com.fdmgroup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
	 * It persists the user
	 * @param user This is the User object
	 */
//	void persist(User user);

	/**
	 * It finds the user by id
	 * @param id This is the user id
	 * @return it returns the User according to the input id
	 */
	Optional<User> findById(int id);

	/**
	 * It finds the user by name
	 * @param username This is the user name
	 * @return It returns the User according to the input name
	 */
	Optional<User> findByUsername(String username);

	/**
	 * It deletes the user by id
	 * @param id this is the id
	 */
	void deleteById(int id);

	/**
	 * It update the user
	 * @param updatedUser this is the User to be updated
	 */
//	void update(User updatedUser);

	/**
	 * It finds all the user
	 * @return It returns all the User in a list
	 */
	List<User> findAll();

}
