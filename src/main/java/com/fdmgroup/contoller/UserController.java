package com.fdmgroup.contoller;



import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fdmgroup.model.Item;
import com.fdmgroup.model.Order;
import com.fdmgroup.model.Product;
import com.fdmgroup.model.User;
import com.fdmgroup.repository.ItemRepository;
import com.fdmgroup.repository.ProductRepository;
import com.fdmgroup.repository.UserRepository;
import com.fdmgroup.service.ProductService;
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
	
	@Autowired
    private ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ItemRepository itemRepository;

	@GetMapping("/") // http://localhost:8080/
	public String slashIndex() {
		return "index";
	}
	
	@GetMapping("/index")
	public String index() {
		return ("index");
	}

	
	@GetMapping("/register")
	public String register() {
		return ("register");
	}
	
	@GetMapping("/product")
	public String product(HttpSession session, Model model) {
		model.addAttribute("products", productService.getAllProducts());
		String user = (String) session.getAttribute("currentUser");
		
//		model.addAttribute("user", user);
		LOGGER.info(user);
		LOGGER.info(session.getAttribute("currentUser").getClass()	); 
		User currentUser = userRepository.findByUsername(user).get();
//		LOGGER.info(user.toString());
		model.addAttribute("user", currentUser);
		return ("product");
	}
	
	@PostMapping("/order")
	public String makeOrder(HttpServletRequest request, HttpSession session) {
		LOGGER.info("start ordering");
		
		String productName = request.getParameter("productName");
		
		String CurrentUsername = request.getParameter("username");
		LOGGER.info("received order: "+ CurrentUsername + productName);
		
		Product product = productRepository.findByName(productName).get();
		LOGGER.info("found "+ product);
		
		Item itemRequest = new Item(product);
		
		itemRepository.save(itemRequest);
		
		Order newOrder = new Order(userService.findUser(CurrentUsername),itemRequest);
		LOGGER.info(newOrder.toString());
		return "redirect:/user/" + CurrentUsername;
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
		LOGGER.info("User registration processing...");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LOGGER.info("New register User : "+ username);
		// Save to DB
		User user = new User(username, password);
		userService.registerNewUser(user);

		return "redirect:/login";
	}
	
	/**
	 * 
	 * 
	 * @param request This is HttpServletRequest
	 * @param session This is HttpSession
	 * @return Go to /user/{username}
	 */
	@PostMapping("/setdetail")
	public String setPersonalDetails(HttpServletRequest request, HttpSession session) {
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String address = request.getParameter("address");
		
		// Save to DB
		
		User userFound = userService.findUser((session.getAttribute("currentUser").toString()));
		LOGGER.info(userFound.getUsername()+ " updated " + "FirstName : " + firstName + " | LastName : " + lastName + "| Address : " + address);
		userRepository.updateUserDetails(userFound.getUsername(),userFound.getPassword(),firstName,lastName,address);
		
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

	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}

	@PostMapping("/login")
	public String processLogin(@RequestParam("username") String username,
	                           @RequestParam("password") String password,
	                           HttpSession session,
	                           RedirectAttributes redirectAttributes) {
		  if (username.equals("admin") && password.equals("admin")) {
	            // Authentication successful; set user session
			  LOGGER.info("Admin Authentication was successful!");
	            session.setAttribute("currentUser", username);
	            
	            return "redirect:/AdminLogin";
		  }else if (userService.containUser(username, password)) {
	        // Authentication successful; set user session
	        session.setAttribute("currentUser", username);
	        LOGGER.info("User " +  username + " successfully logged in");
	        return "redirect:/user/" + username;
	    } else {
	        // Authentication failed
	        redirectAttributes.addAttribute("error", "true");
	        LOGGER.info("invalid username or password input: User name = " + username + " Password = " + password);
	        return "redirect:/login";
	    }
	}
	
	@GetMapping("/AdminLogin")
    public String showProductList(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        
        LOGGER.info(productService.getAllProducts());
        return "admin";
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
		 		LOGGER.info(session.getAttribute("currentUser")	);    	
		    	User user = userService.findUser(username);
		    	
		    	model.addAttribute("user", user);
		    	
		    	return("profile");
	        } else {
	            // User is not logged in, redirect to login page
	            return "redirect:/login";
	        }
		 	
	    	
	    }	
	 
	 
	 @PostMapping("/add")
	    public String addProduct(HttpServletRequest request) {
	    	
	    	String productName = request.getParameter("productName");
			String description = request.getParameter("description");
			
			Product product = new Product(productName, description);
			productService.addProduct(product);
			
			LOGGER.info( "register: " + product.toString()); 
	        return "redirect:/AdminLogin"; // Redirect to the product list page
	    }
}
