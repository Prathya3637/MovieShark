package com.project.movieshark.mapper;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.movieshark.dto.ReviewRequestDTO;
import com.project.movieshark.dto.ReviewResponseDTO;
import com.project.movieshark.entity.Movie;
import com.project.movieshark.entity.Review;
import com.project.movieshark.repository.MovieRepository;


@Component
public class ReviewMapper {
	@Autowired
	private MovieRepository movieRepo;
	
	
	
	public Review toEntity(ReviewRequestDTO request) {
		Movie movie = movieRepo.findByTitleIgnoreCase(request.getMovieTitle()).orElseThrow(()->new NoSuchElementException("Movie is not present in databse"));
		Review review = new Review();
		review.setMovieTitle(movie.getTitle());
		review.setRating(request.getRating());
		review.setReview(request.getReview());
		review.setMovie(movie);
		return review;
	}
	
	public ReviewResponseDTO toResponse(Review review) {
		ReviewResponseDTO responseDTO = new ReviewResponseDTO();
		responseDTO.setId(review.getId());
		responseDTO.setCreatedAt(review.getCreatedAt());
		responseDTO.setMovieName(review.getMovieTitle());
		responseDTO.setMovieTitle(review.getMovieTitle());
		responseDTO.setRating(review.getRating());
		responseDTO.setReview(review.getReview());
		responseDTO.setMovieId(review.getMovie().getId());
		responseDTO.setUpdatedAt(review.getUpdatedAt());
		return responseDTO;
	}	
}
