package com.project.movieshark.repository;




import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.movieshark.entity.Genre;
import com.project.movieshark.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{
public Optional<Movie> findByTitleIgnoreCase(String title);
	
	public List<Movie> findAllByGenre(Genre genre);
}


