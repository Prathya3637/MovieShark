package com.project.movieshark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.project.movieshark.Util.JwtUtil;
import com.project.movieshark.dto.TicketBookingRequestDTO;
import com.project.movieshark.dto.TicketResponseDTO;
import com.project.movieshark.entity.Ticket;
import com.project.movieshark.service.TicketService;


@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/book")
    public ResponseEntity<?> bookTicket(
            @RequestHeader("Authorization") String token,
            @RequestBody TicketBookingRequestDTO request) {

        // Extract token value
        String jwtToken = token.substring(7); // remove "Bearer "
        String userEmail = jwtUtil.extractUsername(jwtToken);

        TicketResponseDTO response = ticketService.bookTicket(request, userEmail);

        return ResponseEntity.ok(response);
    }
}
