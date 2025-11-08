package com.project.movieshark.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.movieshark.dto.TheaterRequestDTO;
import com.project.movieshark.dto.TheaterResponseDTO;
import com.project.movieshark.entity.SeatType;
import com.project.movieshark.entity.Theater;
import com.project.movieshark.entity.TheaterSeat;
import com.project.movieshark.mapper.TheaterMapper;
import com.project.movieshark.repository.TheaterRepository;
import com.project.movieshark.repository.TheaterSeatRepository;

@Service
public class TheaterService {
	@Autowired
	private TheaterRepository theaterRepo;
	
	@Autowired
	private TheaterMapper theaterMapper;
	
	@Autowired
	private TheaterSeatRepository theaterSeatRepo;
	
	public void addTheaterToDB(TheaterRequestDTO requestDTO) {
		Theater theater = theaterMapper.toEntity(requestDTO);
		theaterRepo.save(theater);
		List<TheaterSeat> seats = new ArrayList<>();
		for(int i=0;i<5;i++) {
			TheaterSeat seat = new TheaterSeat();
			seat.setSeatNumber("A"+i);
			seat.setTheater(theater);
			seat.setType(SeatType.GOLD);
			seats.add(seat);
		}
		for(int i=0;i<5;i++) {
			TheaterSeat seat = new TheaterSeat();
			seat.setSeatNumber("B"+i);
			seat.setTheater(theater);
			seat.setType(SeatType.SILVER);
			seats.add(seat);
		}
		for(int i=0;i<5;i++) {
			TheaterSeat seat = new TheaterSeat();
			seat.setSeatNumber("C"+i);
			seat.setTheater(theater);
			seat.setType(SeatType.PREMIUM);
			seats.add(seat);
		}
		theaterSeatRepo.saveAll(seats);
	}
	
	public TheaterResponseDTO deleteTheaterFromDB(Integer id) {
		Theater theater = theaterRepo.findById(id).orElseThrow(()->new NoSuchElementException("Invalid theater id"));
		theaterRepo.delete(theater);;
		return theaterMapper.toResponse(theater);
		
	}
}
