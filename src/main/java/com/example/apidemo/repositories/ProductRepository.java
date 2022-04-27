package com.example.apidemo.repositories;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apidemo.model.Product;

@Configuration
public interface  ProductRepository extends JpaRepository<Product, Long>{

	// spring jpa: findBy + ten thuoc tinh (productName) = findByProductName
	List<Product> findByProductName(String productName);
}
