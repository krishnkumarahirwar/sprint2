package com.capg.bookingmgmt.dto;

import com.capg.bookingmgmt.util.SeatStatus;

public class Seat {
	private int seatId;
	private SeatStatus seatStatus;
	private double seatPrice;
	
	public Seat(int seatId, SeatStatus seatStatus, double seatPrice) {
		super();
		this.seatId = seatId;
		this.seatStatus = seatStatus;
		this.seatPrice = seatPrice;
	}
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public SeatStatus getSeatStatus() {
		return seatStatus;
	}
	public void setSeatStatus(SeatStatus seatStatus) {
		this.seatStatus = seatStatus;
	}
	public double getSeatPrice() {
		return seatPrice;
	}
	public void setSeatPrice(double seatPrice) {
		this.seatPrice = seatPrice;
	}
}
