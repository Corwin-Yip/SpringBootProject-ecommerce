package com.fdmgroup.repo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fdmgroup.model.User;
import com.fdmgroup.repository.UserRepository;





class UserTest {
	
	private User user;
	private UserRepository userRepository;
	@BeforeEach
	public void setUp() {
		
		user = new User("john.smith", "apple123", "John", "Smith", "HK");

	}
	
	
	@Test
	void test() {
		//arrange
		User expected = user;
		
		//act
		userRepository.persist(user);
		
		//assert
		assertEquals(expected, userRepository.findAll().get(0));
	}

}
