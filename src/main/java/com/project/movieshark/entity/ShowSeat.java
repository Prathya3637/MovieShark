package com.project.movieshark.entity;



import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShowSeat {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private SeatType type;
	
	@Column(nullable=false)
	private Double price;
	
	@ManyToOne
	@JoinColumn(name = "show_id", nullable=false)
	private Show show;
	
	@ManyToOne
    @JoinColumn(name = "theater_seat_id", nullable=false)
    private TheaterSeat theaterSeat;
	
	@ManyToMany(mappedBy = "seats")
    private List<Ticket> tickets;
	
	
	@Column(nullable=false)
	private Boolean isBooked;
}
