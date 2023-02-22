package com.revature.clp.ecommerce.project.service;

import java.util.List;

import com.revature.clp.ecommerce.project.dto.OrderDto;
import com.revature.clp.ecommerce.project.dto.UserDto;


public interface EcommService {
	
	// Login
	UserDto findByUsernameAndPassword(UserDto userDto);
	
	// Register User
	UserDto registerUser(UserDto userDto);
	
	// updateCart
	OrderDto updateCart(OrderDto orderDto);
	
	// Checkout
	OrderDto checkOut(OrderDto orderDto);
	
	// User Profile
	UserDto findUserProfile(UserDto userDto);

	// View Previous Orders
	List<OrderDto> findPreviousOrdersById(Integer userID);
	
	

}
