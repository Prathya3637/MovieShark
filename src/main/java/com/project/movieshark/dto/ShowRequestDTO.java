package com.project.movieshark.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowRequestDTO {
	@NotNull(message = "Movie ID is required")
    private Integer movieId;

    @NotNull(message = "Theater ID is required")
    private Integer theaterId;

    @NotNull(message = "Show time is required")
    private LocalDateTime time;

    private Integer totalSeats; 
}
