package com.project.movieshark.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowResponseDTO {
	private Integer id;
	private String movieTitle;
	private String theaterName;
    private LocalDateTime time;
}

