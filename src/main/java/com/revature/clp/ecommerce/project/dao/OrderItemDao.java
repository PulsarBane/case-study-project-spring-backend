package com.revature.clp.ecommerce.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.clp.ecommerce.project.entity.OrderItemEntity;

@Repository
public interface OrderItemDao extends JpaRepository<OrderItemEntity, Integer> {

	
}
