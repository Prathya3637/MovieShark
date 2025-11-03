package com.project.movieshark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.movieshark.dto.TheaterRequestDTO;
import com.project.movieshark.service.TheaterService;

@RestController
@RequestMapping("/theater")
public class TheaterController {
	
	@Autowired
	private TheaterService theaterService;
	
	public ResponseEntity<TheaterRequestDTO> addTheater(TheaterRequestDTO requestDTO){
		theaterService.addTheaterToDB(requestDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(requestDTO);
	}
}
