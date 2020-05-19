package com.capg.bookingmgmt.exceptions;

public class BookingNotFoundException extends RuntimeException{
	public BookingNotFoundException(String msg) {
		super(msg);
	}
}
