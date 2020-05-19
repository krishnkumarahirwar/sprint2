package com.capg.bookingmgmt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.bookingmgmt.entities.Ticket;
import com.capg.bookingmgmt.exceptions.TicketNotFoundException;
import com.capg.bookingmgmt.service.ITicketService;



@RestController
@RequestMapping("/tickets")
public class TicketController {

	private static final Logger Log = LoggerFactory.getLogger(BookingController.class);
	
	@Autowired
	private ITicketService ticketService;
	
	@GetMapping("/get/{id}")
	ResponseEntity<Ticket> fetchTicketById(@PathVariable("id") int ticketId){
		Ticket ticket = ticketService.getTicket(ticketId);
		ResponseEntity<Ticket> response = new ResponseEntity<Ticket>(ticket,HttpStatus.OK);
		return response;
	}
	
	@ExceptionHandler(TicketNotFoundException.class)
	public ResponseEntity<String> handleTicketNotFoundException(TicketNotFoundException exception){
		Log.error("Ticket Exception",exception);
		 String msg = exception.getMessage();
	     ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
	     return response;
	}
	@ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleAll(Throwable ex) {
        Log.error("exception caught", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
}
