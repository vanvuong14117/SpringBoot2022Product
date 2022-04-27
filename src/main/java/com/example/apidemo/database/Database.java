package com.example.apidemo.database;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.apidemo.model.Product;
import com.example.apidemo.repositories.ProductRepository;

@Configuration
public class Database {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(Database.class);

	@Bean
	CommandLineRunner initDatabase(ProductRepository productRepository) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				Product productA = new Product("macbook", 2020, 2000.0, "");
				Product productB = new Product("macbook", 2020, 2000.0, "");
				logger.info("insert data: " + productRepository.save(productA));
				logger.info("insert data: " + productRepository.save(productB));
			}
		};

	}
}
