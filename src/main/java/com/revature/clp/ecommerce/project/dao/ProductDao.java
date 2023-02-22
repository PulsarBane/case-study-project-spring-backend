package com.revature.clp.ecommerce.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.clp.ecommerce.project.entity.ProductEntity;

public interface ProductDao extends JpaRepository<ProductEntity, Integer> {

}
