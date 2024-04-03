package com.fdmgroup.contoller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.model.Product;
import com.fdmgroup.repository.ProductRepository;
import com.fdmgroup.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	private static final Logger LOGGER = LogManager.getLogger(ProductController.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
    private ProductService productService;

    

//	@GetMapping("/products")
//    public String getAllProducts(Model model) {
//        List<Product> products = productService.getAllProducts();
//        model.addAttribute("products", products);
//        LOGGER.info(products);
//        return "product-list";
//    }
    
    @PostMapping("/add")
    public String addProduct(HttpServletRequest request) {
    	
    	String productName = request.getParameter("productName");
		String description = request.getParameter("description");
		
		Product product = new Product(productName, description);
		productService.addProduct(product);
		LOGGER.info("doing");
		LOGGER.info( product.toString()); 
        return "redirect:/admin"; // Redirect to the product list page
    }
    
    
   
}
