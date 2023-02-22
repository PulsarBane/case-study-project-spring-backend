package com.revature.clp.ecommerce.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.clp.ecommerce.project.dao.UserDao;
import com.revature.clp.ecommerce.project.dto.UserDto;
import com.revature.clp.ecommerce.project.entity.UserEntity;
import com.revature.clp.ecommerce.project.service.EcommService;

@CrossOrigin
@RestController
@RequestMapping(value="/user_info")	//http://localhost:8080/e-commerce/user_info
public class UserController {
	
    private UserDao userDao;

	@Autowired
    public UserController(UserDao userDao){
        super();
        this.userDao = userDao;
    }
	
	@Autowired
	EcommService service;

	
	// Login 
	@PutMapping
	public UserDto findByUsernameAndPassword(@RequestBody UserDto userPojo){
		return service.findByUsernameAndPassword(userPojo);
		
	}
	
	// Register User
	@PostMapping
    public ResponseEntity<UserEntity> insertNewBook(@RequestBody UserEntity user){
    	
    	UserEntity newUser = userDao.saveAndFlush(user);
        if(user != null){
            return ResponseEntity.ok().body(newUser);
        } else {
            return ResponseEntity.badRequest().build();
        }
    	
    }
	
}
