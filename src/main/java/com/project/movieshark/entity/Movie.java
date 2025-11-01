package com.project.movieshark.entity;

import java.time.LocalDate;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_id")
	private Integer id;
	
	@Column(nullable = false, unique = true, length=100)
	private String title;
	
	@Enumerated(EnumType.STRING)
    @Column(nullable = false)
	private Genre genre;
	
	
	private Double rating;
	
	@OneToMany(mappedBy = "movie")
	private List<Review> reviews;
	
	@OneToMany(mappedBy = "movie")
	private List<Show> shows;
	
	@Column(nullable = false)
	private LocalDate releaseDate;
	
	@Column(nullable = false)
	private String length;
}
