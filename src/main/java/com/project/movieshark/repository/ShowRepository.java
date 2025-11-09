package com.project.movieshark.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.movieshark.entity.Show;

public interface ShowRepository extends JpaRepository<Show, Integer>{
	
	public List<Show>findByTheater_CityIgnoreCase(String city);
	
	@Query("SELECT s FROM Show s WHERE s.movie.title = :movieName AND s.theater.name = :theaterName AND s.time = :showTime")
	Optional<Show> findShowByDetails(String movieName, String theaterName, LocalDateTime showTime);

}
