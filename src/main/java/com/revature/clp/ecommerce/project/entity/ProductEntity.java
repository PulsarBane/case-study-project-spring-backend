package com.revature.clp.ecommerce.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

@Entity
@Table(name = "products")// Name of Table
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_sku")
	private int productSku;
	
	@Column(name = "product_name")
	private String productName; 
	
	@Column(name = "product_image")
	private String productImage;
	
	@Column(name = "product_quantity")
	private Integer productQuantity;
	
	@Column(name = "product_price")
	private Double productPrice;

	
	
	
}
