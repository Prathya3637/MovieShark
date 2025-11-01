package com.project.movieshark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.movieshark.dto.ReviewRequestDTO;
import com.project.movieshark.dto.ReviewResponseDTO;
import com.project.movieshark.service.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/review")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;

	@PostMapping("/add")
	public ResponseEntity<ReviewRequestDTO> addReview(@Valid @RequestBody ReviewRequestDTO reviewRequest ){
		reviewService.addReviewToDB(reviewRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(reviewRequest);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ReviewResponseDTO> deleteReview(@PathVariable Integer id){
		return ResponseEntity.ok(reviewService.deleteReviewFromDB(id));
	}
	
	@PatchMapping("/edit/{id}")
	public ResponseEntity<ReviewRequestDTO> editReview(@Valid @PathVariable Integer id,@RequestBody ReviewRequestDTO reviewRequest){
		reviewService.editReviewFromDB(id,reviewRequest);
		return ResponseEntity.ok(reviewRequest);
	}
}
