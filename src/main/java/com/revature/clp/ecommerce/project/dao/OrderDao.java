package com.revature.clp.ecommerce.project.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.clp.ecommerce.project.entity.OrderEntity;

@Repository
public interface OrderDao extends JpaRepository<OrderEntity,  Integer> {
	
	// Find an Order by UserID

	List<OrderEntity> findByUserID(Integer userId);
	
	OrderEntity findOrderByUserID(Integer userID);
	
	OrderEntity findByUserIDAndOrderStatus(Integer userId, Boolean orderStatus);

}
