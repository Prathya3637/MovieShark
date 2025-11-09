package com.project.movieshark.dto;


import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketBookingRequestDTO {
	
	 @NotBlank(message = "Movie name is required")
	    private String movieName;

	    @NotBlank(message = "Theater name is required")
	    private String theaterName;

	    @NotNull(message = "Show time is required")
	    private LocalDateTime showTime;

	    @NotEmpty(message = "At least one seat must be selected")
	    private List<Integer> seatIds;
	

}
