package com.fdmgroup.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fdmgroup.repository.OrderRepository;
import com.fdmgroup.repository.UserRepository;
import com.fdmgroup.service.UserService;


@Controller
public class OrderController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserService userService;
	
	
	
	
}
