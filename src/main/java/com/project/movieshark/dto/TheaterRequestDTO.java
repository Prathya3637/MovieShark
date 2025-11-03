package com.project.movieshark.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TheaterRequestDTO {
	@NotBlank(message="Theater name is requried")
	private String name;
	
	@NotBlank(message="Theater state is requried")
	private String state;
	
	@NotBlank(message="Theater city is requried")
	private String city;
	
	@NotBlank(message="Theater address is requried")
	private String address;
}
