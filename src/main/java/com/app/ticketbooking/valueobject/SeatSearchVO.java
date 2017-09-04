package com.app.ticketbooking.valueobject;

import java.util.List;

public class SeatSearchVO {
	private List<Seat> seats;
	// in case of error
	private String errorMessage;

	public SeatSearchVO(List<Seat> seats, String errorMessage) {
		this.seats = seats;
		this.errorMessage = errorMessage;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
