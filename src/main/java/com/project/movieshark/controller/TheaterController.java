package com.project.movieshark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.movieshark.dto.TheaterRequestDTO;
import com.project.movieshark.dto.TheaterResponseDTO;
import com.project.movieshark.service.TheaterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/theater")
public class TheaterController {
	
	@Autowired
	private TheaterService theaterService;
	
	@PostMapping("/add")
	public ResponseEntity<TheaterRequestDTO> addTheater(@Valid @RequestBody TheaterRequestDTO requestDTO){
		theaterService.addTheaterToDB(requestDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(requestDTO);
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<TheaterResponseDTO> deleteTheater(@PathVariable Integer id){
		return ResponseEntity.status(HttpStatus.OK).body(theaterService.deleteTheaterFromDB(id));
	}
}
