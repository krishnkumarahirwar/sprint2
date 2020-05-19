package com.capg.bookingmgmt.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.bookingmgmt.dao.IBookingDao;
import com.capg.bookingmgmt.dao.ITicketDao;
import com.capg.bookingmgmt.dao.ITransactionDao;
import com.capg.bookingmgmt.entities.Booking;
import com.capg.bookingmgmt.entities.Ticket;
import com.capg.bookingmgmt.entities.BookingTransaction;
import com.capg.bookingmgmt.exceptions.BookingNotFoundException;
import com.capg.bookingmgmt.exceptions.TicketNotFoundException;
import com.capg.bookingmgmt.util.TicketStatus;

@Service
@Transactional
public class BookingServiceImpl implements IBookingService{

	@Autowired
	private IBookingDao bookingDao;
	
	@Autowired
	private ITransactionDao transactionDao;
	
	@Autowired
	private ITicketDao ticketDao; 
	
	@Override
	public Booking addBooking(Booking booking) {
		booking = bookingDao.save(booking);
		return booking;
	}

	@Override
	public Booking fetchBookingById(int bookingId) {
		Optional<Booking> option= bookingDao.findById(bookingId);
		if(!option.isPresent()) {
			throw new BookingNotFoundException("Booking id is wrong. No booking exist with this booking id :"+bookingId);
		}
		Booking booking = option.get();
		return booking;
	}

	@Override
	public List<Booking> fetchAllBookings() {
		List<Booking> bookingList = bookingDao.findAll();
		return bookingList;
	}

	@Override
	public String cancelBooking(int bookingId) {
		Booking booking = fetchBookingById(bookingId);
		Ticket ticket = booking.getTicket();
		ticketDao.delete(ticket);
		bookingDao.delete(booking);
		return "Cancelled";
	}
	

	@Override
	public BookingTransaction makePayment(String paymentMethod, double cost) {
		BookingTransaction transaction = new BookingTransaction();
		transaction.setTransactionAmount(cost);
		transaction.setTransactionMethod(paymentMethod);
		transaction = transactionDao.save(transaction);
		return transaction;
	}

	@Override
	public Ticket showTicket(int bookingId) {
		Booking booking = fetchBookingById(bookingId);
		Ticket ticket = booking.getTicket();
		if(ticket==null) {
			throw new TicketNotFoundException("No Ticket has booked yet.");
		}
		return ticket;
	}

	@Override
	public Booking createBooking(Booking booking,String paymentMethod,String screenName) {
		BookingTransaction bookingTransaction = makePayment(paymentMethod,booking.getTotalCost());
		Ticket ticket = createTicket(booking.getSeatIds(),screenName);
		booking.setBookingDate(LocalDate.now());
		booking.setTransactionId(bookingTransaction.getTransactionId());
		booking.setTicket(ticket);
		booking = addBooking(booking);
		return booking;
	}
	public Ticket createTicket(List<Integer> seatIds,String screenName) {
		Ticket ticket= new Ticket();
		ticket.setNoOfSeats(seatIds.size());
		ticket.setScreenName(screenName);
		ticket.setSeatIds(seatIds);
		ticket.setTicketStatus(TicketStatus.BOOKED);
		ticket = ticketDao.save(ticket);
		return ticket;
	}
	
}
