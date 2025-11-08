package com.project.movieshark.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
	
	@NotBlank(message="Name can not be blank")
	private String name;
	
	@Email(message="Requried email")
	@NotBlank(message="Email can not be blank")
	private String email;
	
	@NotBlank(message="Password can not be blank")
	private String password;
	
	private String role;
}
