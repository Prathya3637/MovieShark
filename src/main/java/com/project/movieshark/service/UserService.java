package com.project.movieshark.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.movieshark.dto.UserRequestDTO;
import com.project.movieshark.entity.User;
import com.project.movieshark.mapper.UserMapper;
import com.project.movieshark.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserMapper mapper;
	
	public void saveUserToDB(UserRequestDTO request) {
		User user = mapper.toEntity(request);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
	}
}
