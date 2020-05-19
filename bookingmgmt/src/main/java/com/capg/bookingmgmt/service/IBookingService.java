package com.capg.bookingmgmt.service;

import java.util.List;
import com.capg.bookingmgmt.entities.Booking;
import com.capg.bookingmgmt.entities.Ticket;
import com.capg.bookingmgmt.entities.BookingTransaction;

public interface IBookingService {
	
	Booking addBooking(Booking booking);
	Booking fetchBookingById(int bookingId);
	List<Booking> fetchAllBookings();
	String cancelBooking(int bookingId);
	Ticket showTicket(int bookingId);
	BookingTransaction makePayment(String paymentMethod, double cost);
	Booking createBooking(Booking booking, String paymentMethod,String screenName);
}
