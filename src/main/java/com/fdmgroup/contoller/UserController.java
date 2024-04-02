package com.fdmgroup.contoller;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.fdmgroup.model.User;
import com.fdmgroup.repository.UserRepository;
import com.fdmgroup.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	private static final Logger LOGGER = LogManager.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@GetMapping("/") // http://localhost:8080/
	public String slashIndex() {
		return "index";
	}

	@GetMapping("/register")
	public String register() {
		return ("register");
	}

	/**
	 * 
	 * Get the username and password then save to DB:
	 * 
	 * @param request this is a HttpServletRequest
	 * @return
	 */
	@PostMapping("/register")
	public String processRegistration(HttpServletRequest request) {
		System.out.println("User registration processing...");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LOGGER.info("New register User : "+ username);
		// Save to DB
		User user = new User(username, password);
		userService.registerNewUser(user);

		return "redirect:/login";
	}
	
	@PostMapping("/setdetail")
	public String setPersonalDetails(HttpServletRequest request, HttpSession session) {
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String address = request.getParameter("address");
		
		// Save to DB
		
		User userFound = userService.findUser((session.getAttribute("currentUser").toString()));
		LOGGER.info(userFound.getUsername()+ " updated " + "FirstName : " + firstName + " | LastName : " + lastName + "| Address : " + address);
		userRepository.updateUserDetails(userFound.getUsername(),userFound.getPassword(),firstName,lastName,address);
		
//		User user = new User(username, password);
//		userService.registerNewUser(user);

		return "redirect:/user/"+ userFound.getUsername();
	}
	
	@GetMapping("/setPersonalDetails")
	public String setPersonalDetails() {
		return "setPersonalDetails";
	}
	

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String processLogin(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session) {
		if (userService.containUser(username, password)) {
			
//		Authentication successful; set user session
			System.out.println("Authentication was successful!");
			session.setAttribute("currentUser", username);
			LOGGER.info("User " +  username + " Sucess login");
			return "redirect:/user/" + username;
		} else {
			// Authentication failed
			System.out.println("Authentication failed.");
			return "login";
		}

	}
	

	 
	 @GetMapping("/logout")
	    public String logout(HttpSession session) {
	        // Invalidate the session to log out
		 	LOGGER.info("User logged out.");
	        session.invalidate();
	        return "redirect:/login";
	  }
	 
	 

	 
	 @GetMapping("user/{username}") // This will be coming in on the URL like http://localhost:8080/user/John
	    public String getUser(@PathVariable("username") String username, Model model,HttpSession session) {
		 	if (session.getAttribute("currentUser") != null) {
	            // User is logged in, show dashboard
		 				    	
		    	User user = userService.findUser(username);
		    	System.out.println(user);
		    	model.addAttribute("user", user);
		    	
		    	return("profile");
	        } else {
	            // User is not logged in, redirect to login page
	            return "redirect:/login";
	        }
		 	
	    	
	    }	
}
