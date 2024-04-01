package com.fdmgroup.contoller;

import java.util.Optional;

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
		System.out.println("Username : " + username + " | Password : " + password);
		// Save to DB
		User user = new User(username, password);
		userService.registerNewUser(user);

		return ("redirect:/login");
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
			return "redirect:/dashboard";
		} else {
			// Authentication failed
			System.out.println("Authentication failed.");
			return "login";
		}

	}
	
	 @GetMapping("/dashboard")
	    public String dashboard(HttpSession session) {
	        // Check if user is logged in
	        if (session.getAttribute("currentUser") != null) {
	            // User is logged in, show dashboard
	            return "dashboard";
	        } else {
	            // User is not logged in, redirect to login page
	            return "redirect:/login";
	        }
	    }
	 
	 @GetMapping("/logout")
	    public String logout(HttpSession session) {
	        // Invalidate the session to log out
	    	System.out.println("User logged out.");
	        session.invalidate();
	        return "redirect:/login";
	  }
	 
	 
	 @GetMapping("users/{id}") // This will be coming in on the URL like http://localhost:8080/user/1
	    public String getUser(@PathVariable("id") long userid, Model model) {
	    	System.out.println("Accessing user ID: " + userid);
	    	
	    	User user = userService.findUser(userid);
	    	System.out.println(user);
	    	model.addAttribute("user", user);
	    	
	    	return("profile");
	    }	
}
