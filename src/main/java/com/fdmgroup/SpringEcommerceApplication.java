package com.fdmgroup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * This is the Spring Boot Application class
 */
@SpringBootApplication
public class SpringEcommerceApplication {
	private static final Logger LOGGER = LogManager.getLogger(SpringEcommerceApplication.class);

	/**
	 * Start the spring boot
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

//		setUp.setUp();	

		SpringApplication.run(SpringEcommerceApplication.class, args);
		LOGGER.info("Application Start");

	}
}
