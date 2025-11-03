package com.project.movieshark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.movieshark.entity.Show;

public interface ShowRepository extends JpaRepository<Show, Integer>{
	public List<Show>findAllByCityIgnoreCase(String city);
}
