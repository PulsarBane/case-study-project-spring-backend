package com.revature.clp.ecommerce.project.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.clp.ecommerce.project.dao.ProductDao;
import com.revature.clp.ecommerce.project.entity.ProductEntity;

@CrossOrigin
@RestController
@RequestMapping(value = "/products") // http://localhost:8080/e-commerce/products
public class ProductController {

	private ProductDao productDao;

	public ProductController(ProductDao productDao) {
		super();
		this.productDao = productDao;
	}

	// Display all the products
	@GetMapping
	public ResponseEntity<List<ProductEntity>> getallProducts() {

		List<ProductEntity> products = productDao.findAll();

		if (products != null) {
			return ResponseEntity.ok().body(products);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}

}
