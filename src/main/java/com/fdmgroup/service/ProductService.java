package com.fdmgroup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.model.Product;
import com.fdmgroup.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
    private ProductRepository productRepository;

	 public Product addProduct(Product product) {
	        return productRepository.save(product);
	    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
