package com.project.movieshark.entity;

import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
public class Ticket {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private String allottedSeats;
	
	@Column(nullable=false)
	private Double amount;
	
	@CreationTimestamp
	private Date bookedAt;
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="show_id",nullable=false)
	private Show show;
	
	@ManyToMany
	@JoinTable(
			name = "ticket_showseat",
	        joinColumns = @JoinColumn(name = "ticket_id"),
	        inverseJoinColumns = @JoinColumn(name = "showseat_id")
			  )
	private List<ShowSeat> seats;
	
}
