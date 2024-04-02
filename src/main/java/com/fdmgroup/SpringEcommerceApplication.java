package com.fdmgroup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SpringEcommerceApplication {
	private static final Logger LOGGER = LogManager.getLogger(SpringEcommerceApplication.class);
	public static void main(String[] args) {
		LOGGER.info("Application Start");
		SpringApplication.run(SpringEcommerceApplication.class, args);
		
		LOGGER.info("Application Start");

	}
}
