package com.project.movieshark.mapper;

import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<TheaterResponseDTO> toTheaterResponseList(List<Theater> theaterList){
		return theaterList
						  .stream()
						  .map(this::toResponse)
						  .collect(Collectors.toList());
	}
}
