package com.revature.clp.ecommerce.project.dto;

import jakarta.annotation.Nonnull;
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

public class OrderItemDto {
	
	private int itemId;
	
	@Nonnull
	private Integer orderNo;
	
	@Nonnull
	private Integer productSku;
	

}
