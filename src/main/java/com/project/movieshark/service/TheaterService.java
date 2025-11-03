package com.project.movieshark.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.movieshark.dto.TheaterRequestDTO;
import com.project.movieshark.mapper.TheaterMapper;
import com.project.movieshark.repository.TheaterRepository;

public class TheaterService {
	@Autowired
	private TheaterRepository theaterRepo;
	
	@Autowired
	private TheaterMapper theaterMapper;
	
	public void addTheaterToDB(TheaterRequestDTO requestDTO) {
		theaterRepo.save(theaterMapper.toEntity(requestDTO));
	}
}
