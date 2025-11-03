package com.project.movieshark.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.movieshark.dto.ShowRequestDTO;
import com.project.movieshark.dto.ShowResponseDTO;
import com.project.movieshark.entity.Show;
import com.project.movieshark.mapper.ShowMapper;
import com.project.movieshark.repository.ShowRepository;

@Service
public class ShowService {
	@Autowired
	private ShowRepository showRepo;
	@Autowired
	private ShowMapper showMapper;
	
	public void addShowToDB(ShowRequestDTO requestDTO) {
		showRepo.save(showMapper.toEntity(requestDTO));
	}
	
	public ShowResponseDTO deleteShowFromDB(Integer id) {
		Show show = showRepo.findById(id).orElseThrow(()->new NoSuchElementException("Invalid show id"));
		if(show!=null) {
			showRepo.deleteById(id);
		}
		return showMapper.toResponse(show);
	}
	
	public List<ShowResponseDTO> searchByCityFromDB(String city){
		return showMapper.toResponseDTOList(showRepo.findAllByCityIgnoreCase(city));
	}
}
