package com.project.movieshark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.movieshark.entity.Show;

public interface ShowRepository extends JpaRepository<Show, Integer>{
	@Query("Select s. ")
	public List<Show>findAllByCityIgnoreCase(String city);
}
