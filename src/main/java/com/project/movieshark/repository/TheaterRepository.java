package com.project.movieshark.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.movieshark.entity.Theater;

public interface TheaterRepository extends JpaRepository<Theater, Integer>{
	public Optional<List<Theater>>findByCityIgnoreCase(String city);
}
