package com.capg.bookingmgmt.dto;

import java.util.List;

public class CreateBookingRequest {
	
	private int movieId;
	private int showId;
	private String screenName;
	List<Integer> choosenSeats;
	private String paymentMethod;
	
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getShowId() {
		return showId;
	}
	public void setShowId(int showId) {
		this.showId = showId;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public List<Integer> getChoosenSeats() {
		return choosenSeats;
	}
	public void setChoosenSeats(List<Integer> choosenSeats) {
		this.choosenSeats = choosenSeats;
	}
	
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	
}
