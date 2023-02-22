package com.revature.clp.ecommerce.project.dto;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
// use this constructor for order items
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode


public class ProductDto {
	
	private int productSku;
	
	@Nonnull
	private String productName;
	
	@Nonnull
	private String productImage;
	
	@Nonnull
	private Integer productQuantity;
	
	@Nonnull
	private Double productPrice;

}

