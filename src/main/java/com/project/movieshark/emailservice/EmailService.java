package com.project.movieshark.emailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project.movieshark.entity.Ticket;

@Service
public class EmailService {
	@Autowired
    private JavaMailSender mailSender;

    public void sendTicketEmail(String toEmail, Ticket ticket) {

        String subject = "Your Movie Ticket Confirmation";

        String text = "Hello " + ticket.getUser().getName() + ",\n\n" +
                "Your ticket has been booked successfully!\n\n" +
                "Movie: " + ticket.getShow().getMovie().getTitle() + "\n" +
                "Theater: " + ticket.getShow().getTheater().getName() + "\n" +
                "Show Time: " + ticket.getShow().getTime() + "\n" +
                "Seats: " + ticket.getAllottedSeats() + "\n" +
                "Amount Paid: " + ticket.getAmount() + "\n\n" +
                "Enjoy your movie!\n";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }
}
