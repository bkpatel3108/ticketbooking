package com.app.ticketbooking.listner;

public interface SeatStatusObserver {
	void incrementFreeSeatCount();
	void decrementFreeSeatCount();
}
