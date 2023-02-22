package com.revature.clp.ecommerce.project.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.clp.ecommerce.project.entity.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Integer> {
	
	
	Optional<UserEntity> findByUsernameAndPassword(String username, String Password);
	
	
	
}
