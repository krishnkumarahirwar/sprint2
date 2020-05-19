package com.capg.bookingmgmt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.bookingmgmt.entities.BookingTransaction;

public interface ITransactionDao extends JpaRepository<BookingTransaction, Integer>{

}
