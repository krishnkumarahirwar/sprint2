package com.capg.bookingmgmt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.bookingmgmt.entities.Ticket;

public interface ITicketDao extends JpaRepository<Ticket, Integer>{

}
