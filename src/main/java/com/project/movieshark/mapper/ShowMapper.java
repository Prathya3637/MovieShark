package com.project.movieshark.mapper;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.movieshark.dto.ShowRequestDTO;
import com.project.movieshark.dto.ShowResponseDTO;
import com.project.movieshark.entity.Movie;
import com.project.movieshark.entity.Show;
import com.project.movieshark.entity.Theater;
import com.project.movieshark.repository.MovieRepository;
import com.project.movieshark.repository.TheaterRepository;

@Component
public class ShowMapper {
	@Autowired
	private MovieRepository movieRepo;
	
	@Autowired 
	private TheaterRepository theaterRepo;
	
	public Show toEntity(ShowRequestDTO requestDTO) {
		Show show = new Show();
		Movie movie = movieRepo.findById(requestDTO.getMovieId()).orElseThrow(()->new NoSuchElementException("Invalid movie id"));
		Theater theater = theaterRepo.findById(requestDTO.getTheaterId()).orElseThrow(()-> new NoSuchElementException("Invalid theater id"));
		show.setMovie(movie);
		show.setTheater(theater);
		show.setTime(requestDTO.getTime());
		return show;
	}
	
	public ShowResponseDTO toResponse(Show show) {
		ShowResponseDTO responseDTO = new ShowResponseDTO();
		responseDTO.setId(show.getId());
		responseDTO.setMovieTitle(show.getMovie().getTitle());
		responseDTO.setTheaterName(show.getTheater().getName());
		responseDTO.setTime(show.getTime());
		return responseDTO;
	}
	
	public List<ShowResponseDTO> toResponseDTOList(List<Show> showList){
		return showList
					   .stream()
					   .map(this::toResponse)
					   .collect(Collectors.toList());
	}
}
