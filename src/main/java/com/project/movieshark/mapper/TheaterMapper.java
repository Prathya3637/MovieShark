package com.project.movieshark.mapper;

import com.project.movieshark.dto.TheaterRequestDTO;
import com.project.movieshark.entity.Theater;

public class TheaterMapper {
	public Theater toEntity(TheaterRequestDTO requestDTO) {
		Theater theater = new Theater();
		theater.setName(requestDTO.getName());
		theater.setCity(requestDTO.getCity());
		theater.setState(requestDTO.getState());
		theater.setAddress(requestDTO.getAddress());
		return theater;
	}
}
