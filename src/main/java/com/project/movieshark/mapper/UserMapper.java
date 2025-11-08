package com.project.movieshark.mapper;

import org.springframework.stereotype.Component;

import com.project.movieshark.dto.UserRequestDTO;
import com.project.movieshark.entity.User;

@Component
public class UserMapper {
	public User toEntity(UserRequestDTO request) {
		User user = new User();
		if (request.getRole() == null || request.getRole().isBlank()) {
	        request.setRole("USER");
	    }
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		user.setRole(request.getRole());
		return user;
	}
}
