package com.project.movieshark.dto;

import java.time.LocalDate;

import com.project.movieshark.entity.Genre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieRequestDTO {
	@NotBlank(message = "Movie title is requried!")
	@Size(max = 100, message = "Movie title cannot exceed 100 characters")
	private String title;
	
	@NotNull(message = "Genre is requried!")
	private Genre genre;
	
	@NotNull(message = "Release date is requried!")
	@PastOrPresent(message = "Release date should be in past or present")
	private LocalDate releaseDate;
	
	@NotBlank(message = "Movie length is requried!")
	private String length;
	
	
}
