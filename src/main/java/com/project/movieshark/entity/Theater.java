package com.project.movieshark.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Theater {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String state;
	
	@Column(nullable=false)
	private String city;
	
	@Column(nullable=false)
	private String address;
	
	@OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<TheaterSeat> seats;

    @OneToMany(mappedBy = "theater")
    private List<Show> shows;
}
