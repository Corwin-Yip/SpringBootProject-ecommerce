//package com.fdmgroup.repo;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.fdmgroup.model.User;
//import com.fdmgroup.repository.UserRepository;
//
//
//@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = UserRepo.class)
//@ComponentScan("com.fdmgroup.repository"+"com.fdmgroup.model")
////@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class UserRepo {
//
//private User user;
//	
////	@Autowired
////	private TestEntityManager entityManager;
//	
//	@Autowired
//	private UserRepository userRepository ;
//	
//		
//	@BeforeEach
//	public void setUp() {
//		
//		user = new User("john.smith", "apple123", "John", "Smith", "HK");
//	}
//	
//	
//	@Test
//	void testPersist() {
//		//arrange
//		User expected = user;
//		userRepository.save(user);
////		entityManager.persist(user);
//		
//		//act
//		User actual = userRepository.findByUsername("john.smith").get();
//		
//		//assert
//		assertEquals(expected, actual);
//	}
//}
