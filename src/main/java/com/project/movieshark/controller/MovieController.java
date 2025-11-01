package com.project.movieshark.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.movieshark.dto.MovieRequestDTO;
import com.project.movieshark.dto.MovieResponseDTO;
import com.project.movieshark.entity.Genre;
import com.project.movieshark.service.MovieService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/movie")
public class MovieController {
	@Autowired
	public MovieService movieService;
	
	@PostMapping("/add")
	public ResponseEntity<MovieRequestDTO> addMovie(@Valid @RequestBody MovieRequestDTO requestDTO){
		movieService.addMovieToDB(requestDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(requestDTO);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<MovieResponseDTO>> getAllMovies(){
		return ResponseEntity.status(HttpStatus.OK).body(movieService.getMoviesFromDB());
	}
	
	@GetMapping("/searchbytitle/{title}")
	public ResponseEntity<MovieResponseDTO> getMovieByTitle(@PathVariable String title){
		return ResponseEntity.ok(movieService.getMovieByTitleFromDB(title));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<MovieResponseDTO> deleteMovie(@PathVariable Integer id){
		return ResponseEntity.ok(movieService.deleteMovieFromDB(id));
	}
	
	@GetMapping("/searchbygenre/{genre}")
	public ResponseEntity<List<MovieResponseDTO>> getMovieByGenre(@PathVariable String genre){
		Genre genreEnum;
	    try {
	        genreEnum = Genre.valueOf(genre.toUpperCase()); // normalize case
	    } catch (IllegalArgumentException e) {
	        throw new NoSuchElementException("Invalid genre: " + genre);
	    }
		return ResponseEntity.ok(movieService.getMoviesByGenreFromDB(genreEnum));
	}
	
	@PatchMapping("/updaterating/{id}")
	public ResponseEntity<String> updateRating(@PathVariable Integer id){
		movieService.updateRatingForMovie(id);
		return ResponseEntity.ok("Rating Updated");
	}
}
