package com.fdmgroup.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fdmgroup.contoller.UserController;
import com.fdmgroup.model.Item;
import com.fdmgroup.model.Order;
import com.fdmgroup.model.Product;
import com.fdmgroup.model.User;
import com.fdmgroup.repository.ItemRepository;
import com.fdmgroup.repository.OrderRepository;
import com.fdmgroup.repository.ProductRepository;
import com.fdmgroup.repository.UserRepository;
import com.fdmgroup.service.ProductService;
import com.fdmgroup.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ControllerTest {

	@Autowired
	private UserController userController;

	@Mock
	@Autowired
	private UserRepository userRepository;
	@Mock
	@Autowired
	private UserService userService;
	@Mock
	@Autowired
	private ProductService productService;

	@MockBean
	@Autowired
	private ProductRepository productRepository;
	@MockBean
	@Autowired
	private ItemRepository itemRepository;
	@MockBean
	@Autowired
	private OrderRepository orderRepository;

	@Mock
	private HttpServletRequest request;
	@Mock
	private Model model;
	@Mock
	private HttpSession session;

	private User user1;
	private User user2;

	@BeforeEach
	void setUp() {
		user1 = new User("test", "test");
		user2 = new User("testName", "testPassword", "testFirstName", "testLastName", "testAdress");
		userService.registerNewUser(user2);

		userService = mock(UserService.class);

		userController.userService = userService;

	}

	@Test
	void testProcessRegistration() {
		// Mock the request parameters
		when(request.getParameter("username")).thenReturn("testuser");
		when(request.getParameter("password")).thenReturn("testpassword");

		String result = userController.processRegistration(request);

		// Verify that the userService.registerNewUser method is called with the correct
		// argument
		verify(userService).registerNewUser(new User("testuser", "testpassword"));

		// Verify that the return value is as expected
		assertEquals("redirect:/login", result);
	}

	@Test
	void testGetUser_LoggedInUser() {
		// Mock the session attribute to simulate a logged-in user

		when(session.getAttribute("currentUser")).thenReturn("testuser");

		// Mock the userService.findUser method to return a User object
		when(userService.findUser("testuser")).thenReturn(new User("testuser", "password"));

		String result = userController.getUser("testuser", model, session);

		// Verify that the userService.findUser method is called with the correct
		// argument
		// and the User object is added to the model
		verify(userService).findUser("testuser");
		verify(model).addAttribute("user", new User("testuser", "password"));

		// Verify that the return value is as expected
		assertEquals("profile", result);
	}

	@Test
	void testGetUser_NotLoggedInUser() {
		// Mock the session attribute to simulate no logged-in user

		when(session.getAttribute("currentUser")).thenReturn(null);

		String result = userController.getUser("testuser", model, session);

		// Verify that the userService.findUser method is not called
		verify(userService, Mockito.never()).findUser(Mockito.anyString());

		// Verify that the return value is as expected
		assertEquals("redirect:/login", result);
	}

	@Test
	void testMakeOrder() {
		// Mock the request parameters
		when(request.getParameter("productName")).thenReturn("Test Product");
		when(request.getParameter("username")).thenReturn("testuser");

		// Mock the userService.findUser method to return a User object
		User user = new User("testuser", "test password");
		when(userService.findUser("testuser")).thenReturn(user);

		// Mock the productRepository to return a product
		Product product = new Product("Test Product", "test description");
		Optional<Product> optionalProduct = Optional.of(product);
		when(productRepository.findByName("Test Product")).thenReturn(optionalProduct);

		// Mock the itemRepository.save method
		Item itemRequest = new Item(product);
		when(itemRepository.save(itemRequest)).thenReturn(itemRequest);

		// Mock the orderRepository.save method
		Order newOrder = new Order(user, itemRequest);
		when(orderRepository.save(newOrder)).thenReturn(newOrder);

		String result = userController.makeOrder(request);

		// Verify that the necessary methods are called with the correct arguments
		verify(productRepository).findByName("Test Product");
		verify(userService).findUser("testuser");

		// Verify that the return value is as expected
		assertEquals("redirect:/user/testuser", result);
	}

	@Test
	void testProcessLogin_WithValidAdminCredentials() {
		// Create mock objects for the dependencies
		HttpSession session = mock(HttpSession.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

		// Create an instance of the UserController
		UserController userController = new UserController();

		// Call the method being tested
		String result = userController.processLogin("admin", "admin", session, redirectAttributes);

		// Verify that the session attribute is set correctly
		verify(session).setAttribute("currentUser", "admin");

		// Verify that the redirect URL is as expected
		assertEquals("redirect:/AdminLogin", result);
	}

	@Test
	void testProcessLogin_WithValidUserCredentials() {
		// Create mock objects for the dependencies
		HttpSession session = mock(HttpSession.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		UserService userService = mock(UserService.class);

		// Create an instance of the UserController and set the userService mock
		UserController userController = new UserController();
		userController.userService = userService;

		// Define the user credentials to be used in the test
		String username = "testuser";
		String password = "testpassword";

		// Mock the userService.containUser method to return true
		when(userService.containUser(username, password)).thenReturn(true);

		// Call the method being tested
		String result = userController.processLogin(username, password, session, redirectAttributes);

		// Verify that the session attribute is set correctly
		verify(session).setAttribute("currentUser", username);

		// Verify that the redirect URL is as expected
		assertEquals("redirect:/user/" + username, result);
	}

	@Test
	void testProcessLogin_WithInvalidCredentials() {

		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

		// Define the invalid user credentials to be used in the test
		String username = "invaliduser";
		String password = "invalidpassword";

		// Mock the userService.containUser method to return false
		when(userService.containUser(username, password)).thenReturn(false);

		// Call the method being tested
		String result = userController.processLogin(username, password, session, redirectAttributes);

		// Verify that the redirect attribute is added correctly
		verify(redirectAttributes).addAttribute("error", "true");

		// Verify that the redirect URL is as expected
		assertEquals("redirect:/login", result);
	}

	@Test
	void testSetPersonalDetails() {

		// Create test values for the personal details
		String firstName = "John";
		String lastName = "Doe";
		String address = "123 Main St";

		// Mock the request.getParameter method to return the test values
		when(request.getParameter("firstName")).thenReturn(firstName);
		when(request.getParameter("lastName")).thenReturn(lastName);
		when(request.getParameter("address")).thenReturn(address);

		// Mock the session.getAttribute method to return the test username
		when(session.getAttribute("currentUser")).thenReturn("testuser");

		// Mock the userService.findUser method to return a User object
		User user = new User("testuser", "testpassword");
		when(userService.findUser("testuser")).thenReturn(user);

		// Call the method being tested
		String result = userController.setPersonalDetails(request, session);

		// Verify that the redirect URL is as expected
		assertEquals("redirect:/user/" + user.getUsername(), result);
	}

}
