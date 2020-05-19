package com.capg.bookingmgmt.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.bookingmgmt.dao.ITicketDao;
import com.capg.bookingmgmt.entities.Ticket;
import com.capg.bookingmgmt.exceptions.TicketNotFoundException;
import com.capg.bookingmgmt.util.TicketStatus;

@Service
@Transactional
public class TicketServiceImpl implements ITicketService{

	@Autowired
	private ITicketDao ticketDao;
	
	@Override
	public Ticket addTicket(Ticket ticket) {
		ticket = ticketDao.save(ticket);
		return ticket;
	}

	@Override
	public Ticket getTicket(int ticketId) {
		Optional<Ticket> option= ticketDao.findById(ticketId); 
		if(!option.isPresent()) {
			throw new TicketNotFoundException("No Ticket is present with Id : "+ticketId);
		}
		Ticket ticket= option.get();
		return ticket;
	}

	@Override
	public void cancelTicket(int ticketId) {
		Ticket ticket = ticketDao.findById(ticketId).get();
		ticket.setTicketStatus(TicketStatus.CANCELLED);
		ticketDao.save(ticket);
	}
	
	@Override
	public void removeTicket(Ticket ticket) {
		ticketDao.delete(ticket);
		
	}

	

	
	
	
	
}
