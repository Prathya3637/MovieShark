package com.project.movieshark.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.movieshark.dto.ReviewRequestDTO;
import com.project.movieshark.dto.ReviewResponseDTO;
import com.project.movieshark.entity.Review;
import com.project.movieshark.mapper.ReviewMapper;
import com.project.movieshark.repository.ReviewRepository;


public class ReviewService {
	@Autowired
	private ReviewRepository reviewRepo;
	
	
	
	@Autowired
	private ReviewMapper mapper;
	
	public Review addReviewToDB(ReviewRequestDTO request) {
		return reviewRepo.save(mapper.toEntity(request));
	}
	public ReviewResponseDTO deleteReviewFromDB(Integer id){
		Review review = reviewRepo.findById(id).orElseThrow(()-> new NoSuchElementException("No review found with this id"));
		reviewRepo.delete(review);
		return mapper.toResponse(review);
	}
	
	public Review editReviewFromDB(Integer id,ReviewRequestDTO request) {
		Review review = reviewRepo.findById(id).orElseThrow(()->new NoSuchElementException("No such review present in db")); 
		review.setRating(request.getRating());
		review.setReview(request.getReview());
		return reviewRepo.save(review);
	}
}
