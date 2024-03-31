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

}
