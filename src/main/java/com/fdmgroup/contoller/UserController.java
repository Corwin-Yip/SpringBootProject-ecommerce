package com.fdmgroup.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fdmgroup.model.User;
import com.fdmgroup.repository.UserRepository;
import com.fdmgroup.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/") // http://localhost:8080/
	public String slashIndex() {
		return"index";
	}
	
	@GetMapping("/login")
	public String login() {
		return"login";
	}
	
	@GetMapping("/register")
	public String register() {
		return("register");
	}
	
	
	
	//  Method 2 : Get the username and password then save to DB:	
	@PostMapping("/register")
	public String processRegistration(HttpServletRequest request) {
		System.out.println("User registration processing...");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("Username : " + username + " | Password : " + password);
		// Save to DB...
		User user = new User(username, password);
		userService.registerNewUser(user);
		// Update user
		// return("index"); // NB: The URL will remain with /register, use the return
		// below for it to change to /index, but also enable the @GetMapping method for
		// index.
		// return("redirect:/index");
		// return("redirect:/"); // NB: This will fix the URL
		return ("redirect:/login");
	}

}
