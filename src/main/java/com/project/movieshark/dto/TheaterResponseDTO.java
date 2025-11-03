package com.project.movieshark.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TheaterResponseDTO {
	private Integer id;
	private String name;
	private String state;
	private String city;
	private String address;
}
