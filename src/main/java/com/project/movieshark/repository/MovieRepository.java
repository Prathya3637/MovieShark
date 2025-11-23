package com.project.movieshark.repository;





import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.movieshark.entity.Genre;
import com.project.movieshark.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{
public Optional<Movie> findByTitleIgnoreCase(String title);
	
	public Page<Movie> findAllByGenre(Genre genre,Pageable pageable);
}


