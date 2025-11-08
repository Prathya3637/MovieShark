package com.project.movieshark.mapper;

import org.springframework.stereotype.Component;

import com.project.movieshark.dto.TheaterRequestDTO;
import com.project.movieshark.dto.TheaterResponseDTO;
import com.project.movieshark.entity.Theater;

@Component
public class TheaterMapper {
	public Theater toEntity(TheaterRequestDTO requestDTO) {
		Theater theater = new Theater();
		theater.setName(requestDTO.getName());
		theater.setCity(requestDTO.getCity());
		theater.setState(requestDTO.getState());
		theater.setAddress(requestDTO.getAddress());
		return theater;
	}
	
	public TheaterResponseDTO toResponse(Theater theater) {
		TheaterResponseDTO responseDTO = new TheaterResponseDTO();
		responseDTO.setId(theater.getId());
		responseDTO.setName(theater.getName());
		responseDTO.setCity(theater.getCity());
		responseDTO.setState(theater.getState());
		responseDTO.setAddress(theater.getAddress());
		return responseDTO;
	}
}
