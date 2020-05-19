package com.capg.bookingmgmt.service;




import com.capg.bookingmgmt.entities.Ticket;

public interface ITicketService {

	Ticket addTicket(Ticket ticket);
	Ticket getTicket(int ticketId);
	void cancelTicket(int ticketId);
	void removeTicket(Ticket ticket);
}
