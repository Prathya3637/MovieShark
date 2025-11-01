package com.project.movieshark.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Show {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="movie_id", nullable=false)
	private Movie movie;
	
	@ManyToOne
	@JoinColumn(name="theater_id", nullable=false)
	private Theater theater;
	
	@Column(nullable=false)
	private LocalDateTime time;
	
	@OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
	private List<ShowSeat> seats;

	@OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
	private List<Ticket> tickets;
}
