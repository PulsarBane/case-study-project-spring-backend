package com.revature.clp.ecommerce.project.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.revature.clp.ecommerce.project.entity.ProductEntity;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode

public class OrderDto {


	private int orderNo;

	@Nonnull
	private Integer userID;

	@Nonnull
	private LocalDateTime orderDate = LocalDateTime.now();

	@Nonnull
	private Boolean orderStatus;

	@Nonnull
	private List<OrderItemDto> orderItems = new ArrayList<OrderItemDto>();

	@Nonnull
	List<ProductEntity> allProducts;

}
