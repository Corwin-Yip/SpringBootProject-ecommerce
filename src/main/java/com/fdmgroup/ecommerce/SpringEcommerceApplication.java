package com.fdmgroup.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class SpringEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEcommerceApplication.class, args);

	}

}
