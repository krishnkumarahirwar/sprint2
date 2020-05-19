package com.capg.bookingmgmt.exceptions;

public class TicketNotFoundException extends RuntimeException {
	public TicketNotFoundException(String msg) {
		super(msg);
	}
}
