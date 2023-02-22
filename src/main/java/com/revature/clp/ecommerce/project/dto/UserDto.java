package com.revature.clp.ecommerce.project.dto;


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

public class UserDto {
	
	private int userID;
	
	@Nonnull
	private String username;
	@Nonnull
	private String password;
	@Nonnull
	private String firstname;
	@Nonnull
	private String lastname;
	@Nonnull
	private String email;
	@Nonnull
	private String usertype;
	
}
