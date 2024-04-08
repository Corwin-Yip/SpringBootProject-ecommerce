package com.fdmgroup;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CleanDataBase {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("E-commerce_mySQL");
	static EntityManager em = emf.createEntityManager();

	public static void main(String[] args) {

	}

}