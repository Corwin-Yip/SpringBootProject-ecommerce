package com.fdmgroup.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.model.User;
import com.fdmgroup.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public boolean registerNewUser(User user) {
		// Check if the user already exists:
		Optional<User> returnedUser = userRepository.findByUsername(user.getUsername());
		
		// If not, then save to the DB, otherwise fail
		if (returnedUser.isEmpty()) {
			userRepository.save(user);
			return true;
		}
		else {
			return false;
		}
	}
	
	public User findUser(int userid) {
		Optional<User> foundUser = userRepository.findById(userid);
		// Return the user found by ID search or a default one
		return foundUser.orElse(new User("default", "defaultpassword"));
	}
	
	public User findUser(String username) {
		Optional<User> foundUser = userRepository.findByUsername(username);
		// Return the user found by username search or a default one
		return foundUser.orElse(new User("default", "defaultpassword"));
	}
	
	public Boolean containUser (String username, String password) {
		User foundUser = findUser(username);
		
		if (!userRepository.findAll().contains(foundUser)) 
			return false;
		else if (!foundUser.getPassword().equals(password) )
			return false;
		else
			return true;
		
	}
}
