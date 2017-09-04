package com.app.ticketbooking.valueobject;
import java.util.ArrayList;
import java.util.List;

import com.app.ticketbooking.listner.SeatStatusObserver;

public class SeatRow implements SeatStatusObserver {

	// For simplicity, used as int instead of string
	int rowId;

	List<Seat> seats;
	int freeSeats;

	public SeatRow(int rowId) {
		this.rowId = rowId;
	}

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public List<Seat> getSeats() {
		if (seats == null)
			seats = new ArrayList<Seat>();
		return seats;
	}

	public int getFreeSeats() {
		return freeSeats;
	}

	public void setFreeSeats(int freeSeats) {
		this.freeSeats = freeSeats;
	}

	public void incrementFreeSeatCount() {
		// TODO Auto-generated method stub
		freeSeats++;
	}

	public void decrementFreeSeatCount() {
		// TODO Auto-generated method stub
		freeSeats--;
	}

}
