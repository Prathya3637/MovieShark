package com.project.movieshark.dto;

import java.time.LocalDate;
import java.util.List;
import com.project.movieshark.entity.Genre;
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
public class MovieResponseDTO {
	private Integer movieId;
    private String title;
    private Genre genre;
    private Double rating;
    private LocalDate releaseDate;
    private String length;
    private List<ReviewResponseDTO> reviews;
    private List<ShowResponseDTO> shows;
}
