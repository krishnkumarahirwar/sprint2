package com.capg.bookingmgmt.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.bookingmgmt.dto.BookingDetails;
import com.capg.bookingmgmt.dto.CreateBookingRequest;
import com.capg.bookingmgmt.dto.Seat;
import com.capg.bookingmgmt.dto.TicketDto;
import com.capg.bookingmgmt.entities.Booking;
import com.capg.bookingmgmt.entities.Ticket;
import com.capg.bookingmgmt.exceptions.BookingNotFoundException;
import com.capg.bookingmgmt.exceptions.TicketNotFoundException;
import com.capg.bookingmgmt.service.IBookingService;
import com.capg.bookingmgmt.util.SeatStatus;


@RestController
@RequestMapping("/bookings")
public class BookingController {
	
	private static final Logger Log = LoggerFactory.getLogger(BookingController.class);
	
	@Autowired
	private IBookingService bookingService;
	
	
	@PostMapping("/add")
	ResponseEntity<BookingDetails> bookingprocess(@RequestBody CreateBookingRequest bookingDto){
		List<Seat> seats = chooseSeats(bookingDto.getChoosenSeats());
		double cost = getCost(seats);
		
		Booking booking=convertBookingDto(bookingDto,cost);
		booking = bookingService.createBooking(booking,bookingDto.getPaymentMethod(),bookingDto.getScreenName());
		
		BookingDetails bookingDetails = convertBooking(booking);
		ResponseEntity<BookingDetails> response = new ResponseEntity<BookingDetails>(bookingDetails,HttpStatus.OK);
		return response;
	}
	
	@GetMapping
	ResponseEntity<List<Booking>> fetchAllBookings(){
		List<Booking> bookingList = bookingService.fetchAllBookings();
		ResponseEntity<List<Booking>> response = new ResponseEntity<List<Booking>>(bookingList,HttpStatus.OK);
		return response;
	}
	@GetMapping("/getTicket/{id}")
	ResponseEntity<TicketDto> fetchTicket(@PathVariable("id") int bookingId){
		Ticket ticket = bookingService.showTicket(bookingId);
		TicketDto ticketDto=convertTicketDto(ticket);
		ResponseEntity<TicketDto> response = new ResponseEntity<TicketDto>(ticketDto,HttpStatus.OK);
		return response;
	}
	@GetMapping("/get/{id}")
	ResponseEntity<Booking> fetchBooking(@PathVariable("id") int bookingId){
		Booking booking = bookingService.fetchBookingById(bookingId);
		ResponseEntity<Booking> response = new ResponseEntity<Booking>(booking,HttpStatus.OK);
		return response;
	}
	
	@PutMapping("/cancel/{id}")
	ResponseEntity<TicketDto> cancelBooking(@PathVariable("id") int bookingId){
		bookingService.cancelBooking(bookingId);
		TicketDto msg=null;
		ResponseEntity<TicketDto> response = new ResponseEntity<TicketDto>(msg,HttpStatus.OK);
		return response;
	}
	

	public List<Seat> chooseSeats(List<Integer> seatIds) {
		List<Seat> seats = new ArrayList<Seat>();
		for(Integer id:seatIds) {
			Seat seat = new Seat(id, SeatStatus.BOOKED, 1500);
			seats.add(seat);
		}
		return seats;
	}
	
	public Booking convertBookingDto(CreateBookingRequest bookingDto,double cost) {
		Booking booking = new Booking();
		booking.setMovieId(bookingDto.getMovieId());
		booking.setShowId(bookingDto.getShowId());
		booking.setSeatIds(bookingDto.getChoosenSeats());
		booking.setTotalCost(cost);
		return booking;
	}
	public BookingDetails convertBooking(Booking booking) {
		BookingDetails bookingDetails = new BookingDetails(booking.getBookingId(), booking.getMovieId(), 
				booking.getShowId(), booking.getBookingDate(),
				booking.getTransactionId(), booking.getTotalCost(), booking.getSeatIds());
		return bookingDetails;
	}
	
	public double getCost(List<Seat> seatList) {
		double price=0;
		for(Seat seat:seatList) {
			price=price+seat.getSeatPrice();
		}
		return price;
	}
	
	public TicketDto convertTicketDto(Ticket ticket) {
		TicketDto ticketDto = new TicketDto(ticket.getTicketId(),ticket.getNoOfSeats(),ticket.getSeatIds(),ticket.getScreenName());
		return ticketDto;
	}
	
	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<String> handleBookingNotFoundException(BookingNotFoundException exception){
		Log.error("Booking Exception",exception);
		 String msg = exception.getMessage();
	     ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
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
