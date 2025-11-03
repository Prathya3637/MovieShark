package com.project.movieshark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.movieshark.dto.ShowRequestDTO;
import com.project.movieshark.dto.ShowResponseDTO;
import com.project.movieshark.service.ShowService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/show")
public class ShowController {
	@Autowired
	ShowService showService;
	
	@PostMapping("/add")
	public ResponseEntity<ShowRequestDTO> addShow(@Valid @RequestBody ShowRequestDTO requestDTO){
		showService.addShowToDB(requestDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(requestDTO);
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<ShowResponseDTO> deleteShow(@PathVariable Integer id){
		ShowResponseDTO responseDTO = showService.deleteShowFromDB(id);
		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	
	@GetMapping("/search/{city}")
	public ResponseEntity<List<ShowResponseDTO>> searchByCity(@PathVariable String city){
		return ResponseEntity.status(HttpStatus.OK).body(showService.searchByCityFromDB(city));
	}
}
