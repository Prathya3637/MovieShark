package com.project.movieshark.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class TicketResponseDTO {
	 private Integer ticketId;
	 private String showName;
	 private String theaterName;
	 private String seats;
	 private Double amount;
	 private Date bookedAt;
}
