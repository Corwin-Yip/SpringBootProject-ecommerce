package com.fdmgroup.repo;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fdmgroup.model.User;
import com.fdmgroup.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)

public class UserRepoTest {

	private User user;

	@Autowired
	private UserRepository userRepository;

	@BeforeEach
	public void setUp() {

		user = new User("john.smith", "apple123", "John", "Smith", "HK");
	}

	@Test
	void testPersist() {
		// arrange
		User expected = user;
		userRepository.save(user);

		// act
		User actual = userRepository.findByUsername("john.smith").get();

		// assert
		assertEquals(expected, actual);
	}

}
