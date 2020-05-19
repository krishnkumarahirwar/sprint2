package com.capg.bookingmgmt.dto;

import java.util.List;


public class TicketDto {
	private int ticketId;
	private int noOfSeats;
	private List<Integer> seatIds;
	private String screenName;
	
	
	
	public TicketDto(int ticketId, int noOfSeats, List<Integer> seatIds, String screenName) {
		super();
		this.ticketId = ticketId;
		this.noOfSeats = noOfSeats;
		this.seatIds = seatIds;
		this.screenName = screenName;
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public int getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	public List<Integer> getSeatIds() {
		return seatIds;
	}
	public void setSeatIds(List<Integer> seatIds) {
		this.seatIds = seatIds;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	
}
