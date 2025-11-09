package com.project.movieshark.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.movieshark.dto.TicketBookingRequestDTO;
import com.project.movieshark.dto.TicketResponseDTO;
import com.project.movieshark.entity.Show;
import com.project.movieshark.entity.ShowSeat;
import com.project.movieshark.entity.Ticket;
import com.project.movieshark.entity.User;
import com.project.movieshark.repository.ShowRepository;
import com.project.movieshark.repository.ShowSeatRepository;
import com.project.movieshark.repository.TicketRepository;
import com.project.movieshark.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TicketService {
	 	@Autowired
	    private UserRepository userRepo;

	    @Autowired
	    private ShowRepository showRepo;

	    @Autowired
	    private ShowSeatRepository showSeatRepo;

	    @Autowired
	    private TicketRepository ticketRepo;

	    public TicketResponseDTO bookTicket(TicketBookingRequestDTO request, String userEmail) {
	        // 1️⃣ Find user
	        User user = userRepo.findByEmail(userEmail)
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        // 2️⃣ Find show by details
	        Show show = showRepo.findShowByDetails(
	                        request.getMovieName(),
	                        request.getTheaterName(),
	                        request.getShowTime())
	                .orElseThrow(() -> new RuntimeException("Show not found"));

	        // 3️⃣ Fetch and validate seats
	        List<ShowSeat> selectedSeats = showSeatRepo.findAllById(request.getSeatIds());

	        if (selectedSeats.isEmpty()) {
	            throw new RuntimeException("No seats found for given IDs");
	        }

	        for (ShowSeat seat : selectedSeats) {
	            if (!seat.getShow().getId().equals(show.getId())) {
	                throw new RuntimeException("Seat does not belong to this show");
	            }
	            if (seat.getIsBooked()) {
	                throw new RuntimeException("Seat " + seat.getTheaterSeat().getSeatNumber() + " already booked");
	            }
	        }

	        // 4️⃣ Calculate total amount
	        double totalAmount = selectedSeats.stream()
	                .mapToDouble(ShowSeat::getPrice)
	                .sum();

	        // 5️⃣ Create and save Ticket
	        Ticket ticket = new Ticket();
	        ticket.setUser(user);
	        ticket.setShow(show);
	        ticket.setSeats(selectedSeats);
	        ticket.setAmount(totalAmount);
	        ticket.setAllottedSeats(selectedSeats.stream()
	                .map(seat -> seat.getTheaterSeat().getSeatNumber())
	                .reduce((a, b) -> a + ", " + b)
	                .orElse(""));

	        ticketRepo.save(ticket);

	        // 6️⃣ Mark seats as booked
	        selectedSeats.forEach(seat -> seat.setIsBooked(true));
	        showSeatRepo.saveAll(selectedSeats);

	        // 7️⃣ Return response
	        TicketResponseDTO response = new TicketResponseDTO();
	        response.setTicketId(ticket.getId());
	        response.setShowName(show.getMovie().getTitle());
	        response.setTheaterName(show.getTheater().getName());
	        response.setSeats(ticket.getAllottedSeats());
	        response.setAmount(totalAmount);
	        response.setBookedAt(ticket.getBookedAt());

	        return response;
	    }
}
