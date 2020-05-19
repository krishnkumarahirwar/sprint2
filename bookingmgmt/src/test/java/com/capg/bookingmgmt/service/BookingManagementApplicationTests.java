package com.capg.bookingmgmt.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capg.bookingmgmt.dao.ITicketDao;
import com.capg.bookingmgmt.entities.Booking;
import com.capg.bookingmgmt.entities.Ticket;
import com.capg.bookingmgmt.exceptions.BookingNotFoundException;
import com.capg.bookingmgmt.util.TicketStatus;


  @DataJpaTest
  @ExtendWith(SpringExtension.class)
  @Import(BookingServiceImpl.class)
 
class BookingManagementApplicationTests {

	@Autowired
	private IBookingService bookingService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ITicketDao ticketDao;
	
	@Test
	public void fetchBooking_1() {
		Executable executable = () -> bookingService.fetchBookingById(45);
		Assertions.assertThrows(BookingNotFoundException.class, executable);
	}
	
	@Test
	public void fetchBooking_2() {
		Booking booking = createBooking();
		Booking result=bookingService.fetchBookingById(6);
		Assertions.assertEquals(booking, result);
	}
	
	@Test
	public void cancelBooking_1() {
		Executable executable = () -> bookingService.cancelBooking(87);
		Assertions.assertThrows(BookingNotFoundException.class, executable);
	}
	
	@Test
	public void cancelBooking_2() {
		Booking booking = createBooking();
		String result = bookingService.cancelBooking(booking.getBookingId());
		String expected="Cancelled";
		Assertions.assertEquals(result, expected);
	}
	
	@Test
	public void showTicket() {
		Executable executable = () -> bookingService.showTicket(95);
		Assertions.assertThrows(BookingNotFoundException.class, executable);
	}
	
	@Test
	public void showTicket_2() {
		Booking booking = createBooking();
		Ticket result = bookingService.showTicket(booking.getBookingId());
		Assertions.assertEquals(result, booking.getTicket());
	}
	public Booking createBooking() {
		Booking booking = new Booking();
		booking.setMovieId(4578);
		booking.setShowId(7849);
		List<Integer> seatIds = new ArrayList<Integer>();
		seatIds.add(1);
		seatIds.add(2);
		booking.setSeatIds(seatIds);
		booking.setTotalCost(5487);
		booking.setBookingDate(LocalDate.now());
		booking.setTransactionId(45612);
		booking.setTicket(createTicket());
		booking = entityManager.merge(booking);
		return booking;
	}
	public Ticket createTicket() {
		Ticket ticket = new Ticket();
		ticket.setNoOfSeats(3);
		ticket.setScreenName("Gold");
		ticket.setTicketStatus(TicketStatus.BOOKED);
		List<Integer> seatsIds= new ArrayList<Integer>();
		seatsIds.add(1);
		seatsIds.add(2);
		seatsIds.add(3);
		ticket.setSeatIds(seatsIds);
		ticket=ticketDao.save(ticket);
		return ticket;
	}
}
