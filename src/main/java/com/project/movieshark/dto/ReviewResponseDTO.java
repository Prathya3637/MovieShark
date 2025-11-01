package com.project.movieshark.dto;

import java.time.LocalDate;
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
public class ReviewResponseDTO {
	private Integer id;
    private String movieTitle;
    private Double rating;
    private String review;
    private Integer movieId;   // reference to which movie this belongs
    private String movieName;  // fetched from Movie entity for frontend display
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
