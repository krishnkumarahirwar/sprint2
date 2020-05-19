package com.capg.bookingmgmt.entities;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.capg.bookingmgmt.util.TicketStatus;

@Entity
public class Ticket {
	@Id
	@GeneratedValue
	private int ticketId;
	private int noOfSeats;
	@ElementCollection
	private List<Integer> seatIds;
	private TicketStatus ticketStatus;
	private String screenName;
	
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
	public TicketStatus isTicketStatus() {
		return ticketStatus;
	}
	public void setTicketStatus(TicketStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + noOfSeats;
		result = prime * result + ((screenName == null) ? 0 : screenName.hashCode());
		result = prime * result + ((seatIds == null) ? 0 : seatIds.hashCode());
		result = prime * result + ticketId;
		result = prime * result + ((ticketStatus == null) ? 0 : ticketStatus.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (noOfSeats != other.noOfSeats)
			return false;
		if (screenName == null) {
			if (other.screenName != null)
				return false;
		} else if (!screenName.equals(other.screenName))
			return false;
		if (seatIds == null) {
			if (other.seatIds != null)
				return false;
		} else if (!seatIds.equals(other.seatIds))
			return false;
		if (ticketId != other.ticketId)
			return false;
		if (ticketStatus != other.ticketStatus)
			return false;
		return true;
	}
	
	
}
