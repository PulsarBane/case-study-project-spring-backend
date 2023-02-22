package com.revature.clp.ecommerce.project.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "orders") // When dealing with values from two tables I am not sure which table we refer to here...
public class OrderEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_no")
	private int orderNo;
	
	@Column(name = "user_Id")
	private Integer userID;
	
	@Column(name = "order_date")
	private LocalDateTime orderDate = LocalDateTime.now();
	
	@Column(name = "order_status")
	private Boolean orderStatus;
	
	@OneToMany
	@JoinColumn(name="order_no")
	private List<OrderItemEntity> orderItems = new ArrayList<OrderItemEntity>();
	
	@ManyToMany
	@JoinTable(name="order_items", joinColumns = @JoinColumn(name = "order_no"), inverseJoinColumns = @JoinColumn(name = "product_sku"))
	List<ProductEntity> allProducts;
	
}
