package com.revature.clp.ecommerce.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.clp.ecommerce.project.dto.OrderDto;
import com.revature.clp.ecommerce.project.service.EcommService;

@CrossOrigin
@RestController
@RequestMapping(value="/orders")	//http://localhost:8080/e-commerce/orders
public class OrderController {
	
	@Autowired
	EcommService service;
	
	// Cart
	@PutMapping("update")
	public OrderDto updateCart(@RequestBody OrderDto orderDto) {
		return service.updateCart(orderDto);
	}

	// Checkout
	@PutMapping("checkout")
	public OrderDto checkOut(@RequestBody OrderDto orderDto) {
		return service.checkOut(orderDto);
	}
	
	// View Previous Order
	@GetMapping("/history/{userID}")
	List<OrderDto> findPreviousOrderById(@PathVariable("userID") Integer userID) {
		return service.findPreviousOrdersById(userID);

	}

}
