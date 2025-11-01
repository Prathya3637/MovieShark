package com.project.movieshark.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.project.movieshark.dto.MovieRequestDTO;
import com.project.movieshark.dto.MovieResponseDTO;
import com.project.movieshark.dto.ReviewResponseDTO;
import com.project.movieshark.entity.Movie;

@Component
public class MovieMapper {
	public Movie toEntity(MovieRequestDTO dto) {
		Movie movie = new Movie();
	    movie.setTitle(dto.getTitle());
	    movie.setGenre(dto.getGenre());
	    movie.setRating(0.0);
	    movie.setReleaseDate(dto.getReleaseDate());
	    movie.setLength(dto.getLength());
	    return movie;
	}

	    // Entity → ResponseDTO
	public MovieResponseDTO toResponse(Movie movie) {
	    MovieResponseDTO dto = new MovieResponseDTO();
	    dto.setMovieId(movie.getId());
	    dto.setTitle(movie.getTitle());
	    dto.setGenre(movie.getGenre());
	    dto.setRating(movie.getRating());
	    dto.setReleaseDate(movie.getReleaseDate());
	    dto.setLength(movie.getLength());

	        // Nested conversion: reviews (if present)
	    if(movie.getReviews()!=null) {
	    dto.setReviews(
	    		movie.getReviews()
	    			 .stream()
	    			 .map( review ->{
	    				 ReviewResponseDTO r = new ReviewResponseDTO();
	    				 r.setId(review.getId());
	    				 r.setMovieTitle(review.getMovieTitle());
	    				 r.setRating(review.getRating());
	    				 r.setReview(review.getReview());
	    				 r.setCreatedAt(review.getCreatedAt());
	    	             r.setUpdatedAt(review.getUpdatedAt());
	    	             if (review.getMovie() != null) {
	    	                 r.setMovieId(review.getMovie().getId());
	    	                 r.setMovieName(review.getMovie().getTitle());
	    	             }
	    				 return r;
	    			 })
	    			 .collect(Collectors.toList())
	    		);
	    }
	    return dto;
	}
	
	public List<MovieResponseDTO> toMovieResponseList(List<Movie> movieList){
		return movieList
					   .stream()
					   .map(this::toResponse)
					   .collect(Collectors.toList());
	}
}
