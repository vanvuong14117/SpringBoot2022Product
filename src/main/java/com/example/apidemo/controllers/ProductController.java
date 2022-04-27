package com.example.apidemo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apidemo.model.Product;
import com.example.apidemo.model.ResponseObject;
import com.example.apidemo.repositories.ProductRepository;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {

	// DI = dependency injection
	@Autowired
	private ProductRepository _productRepository;

	// http:..api/v1/products/getAllProduct
	@GetMapping("/getAllProducts")
	List<Product> getAllProducts() {
		return _productRepository.findAll();
	}

	// return kieu 1 object with : data : message: status
	@GetMapping("/getProduct/{id}")
	// optional tra ve co the null Optional<Product>
	public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
		Optional<Product> product = _productRepository.findById(id);
		if (product.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "query products successfully", product));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject("false", "can't find products " + id, ""));
		}
		// return _productRepository.findById(id)
		// .orElseThrow(() -> new RuntimeException("can not find product with id = " +
		// id));
	}

	// Product [id=2, productName=macbook, year=2020, price=2000.0, url=]
	@PostMapping("/insert")
	public ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newPostProduct) {
		List<Product> listProduct = _productRepository.findByProductName(newPostProduct.getProductName().trim());
		if (listProduct.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("failed", "Product is exists", ""));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject("ok", "insert products successfully", _productRepository.save(newPostProduct)));

	}
}
