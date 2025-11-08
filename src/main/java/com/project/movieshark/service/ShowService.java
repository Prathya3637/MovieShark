package com.project.movieshark.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.movieshark.dto.ShowRequestDTO;
import com.project.movieshark.dto.ShowResponseDTO;
import com.project.movieshark.entity.Show;
import com.project.movieshark.entity.ShowSeat;
import com.project.movieshark.entity.TheaterSeat;
import com.project.movieshark.mapper.ShowMapper;
import com.project.movieshark.repository.ShowRepository;
import com.project.movieshark.repository.ShowSeatRepository;

@Service
public class ShowService {
	@Autowired
	private ShowRepository showRepo;
	@Autowired
	private ShowMapper showMapper;
	@Autowired
	private ShowSeatRepository showSeatRepo;
	
	public void addShowToDB(ShowRequestDTO requestDTO) {
		Show show = showMapper.toEntity(requestDTO);
		showRepo.save(show);
		
		List<TheaterSeat> theaterSeats = show.getTheater().getSeats();
		List<ShowSeat> showSeats = new ArrayList<>();
		for (TheaterSeat theaterSeat : theaterSeats) {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setTheaterSeat(theaterSeat);
            showSeat.setShow(show);
            showSeat.setType(theaterSeat.getType());
            showSeat.setIsBooked(false);
            double price = 0;
            switch (theaterSeat.getType()) {
                case SILVER:
                    price = 200.0;
                    break;
                case GOLD:
                    price = 300.0;
                    break;
                case PREMIUM:
                    price = 400.0;
                    break;
            }
            showSeat.setPrice(price);
            showSeats.add(showSeat);
		}
		showSeatRepo.saveAll(showSeats);
		show.setSeats(showSeats);
        showRepo.save(show);
	}
	
	public ShowResponseDTO deleteShowFromDB(Integer id) {
		Show show = showRepo.findById(id).orElseThrow(()->new NoSuchElementException("Invalid show id"));
		if(show!=null) {
			showRepo.deleteById(id);
		}
		return showMapper.toResponse(show);
	}
	
	public List<ShowResponseDTO> searchByCityFromDB(String city){
		return showMapper.toResponseDTOList(showRepo.findByTheater_CityIgnoreCase(city));
	}
}
