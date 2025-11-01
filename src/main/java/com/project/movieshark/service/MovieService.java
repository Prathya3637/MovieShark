package com.project.movieshark.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.movieshark.dto.MovieRequestDTO;
import com.project.movieshark.dto.MovieResponseDTO;
import com.project.movieshark.entity.Genre;
import com.project.movieshark.entity.Movie;
import com.project.movieshark.entity.Review;
import com.project.movieshark.mapper.MovieMapper;
import com.project.movieshark.repository.MovieRepository;


@Service
public class MovieService {
	@Autowired
	public MovieRepository movieRepo;
	
	@Autowired
	public MovieMapper mapper;
	
	public Movie addMovieToDB(MovieRequestDTO requestDTO) {
		Movie movie = mapper.toEntity(requestDTO);
		return movieRepo.save(movie);
	}
	
	public List<MovieResponseDTO> getMoviesFromDB() {
		List<Movie> movieList = movieRepo.findAll();
		return mapper.toMovieResponseList(movieList);
	}
	
	public MovieResponseDTO getMovieByTitleFromDB(String name) {
		return movieRepo.findByTitleIgnoreCase(name)
					    .map(mapper::toResponse)
						.orElseThrow(()->new NoSuchElementException("Movie not available"));
		
	}
	@Transactional
	public MovieResponseDTO deleteMovieFromDB(Integer id) {
		return movieRepo.findById(id)
						.map(movie->{
							if(movie.getReviews() != null) {
								movie.getReviews().size();
							}
							movieRepo.deleteById(id);
							return mapper.toResponse(movie);
						})
						.orElseThrow(()-> new NoSuchElementException("Invalid Movie Id"));
	}
	
	public List<MovieResponseDTO> getMoviesByGenreFromDB(Genre genre){
		List<Movie> movies = movieRepo.findAllByGenre(genre);
		if(movies.isEmpty()) {
			throw new NoSuchElementException("No movie with this genre");
		}
		return mapper.toMovieResponseList(movies);
	}
	
	public Movie updateRatingForMovie(Integer id) {
		Movie movie = movieRepo.findById(id).orElseThrow(()->new NoSuchElementException("No such movie present in database"));
		Double avgRating = movie.getReviews()
								.stream()
								.mapToDouble(Review::getRating)
								.average().getAsDouble();
		movie.setRating(avgRating);
		return movieRepo.save(movie);
	}
}
