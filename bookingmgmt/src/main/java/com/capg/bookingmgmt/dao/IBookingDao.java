package com.capg.bookingmgmt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.bookingmgmt.entities.Booking;

public interface IBookingDao extends JpaRepository<Booking, Integer>{

}
