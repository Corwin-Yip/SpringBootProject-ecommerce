package com.fdmgroup.service;

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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ServiceTest {

	@Autowired
	private UserService userService;

	private User user2;

	@BeforeEach
	public void setUp() {

		user2 = new User("testName", "testPassword", "testFirstName", "testLastName", "testAdress");
		userService.registerNewUser(user2);

	}

	@Test
	public void registerNewUser() {
		boolean actual, expected = false;

		// act
		actual = userService.registerNewUser(user2);

		// assert
		assertEquals(expected, actual);

	}

}
