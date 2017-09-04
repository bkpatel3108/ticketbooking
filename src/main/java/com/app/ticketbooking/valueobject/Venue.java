package com.app.ticketbooking.valueobject;

import java.util.ArrayList;
import java.util.List;

import com.app.ticketbooking.listner.SeatStatusObserver;

public class Venue implements SeatStatusObserver {

	private static Venue venue;
	private List<SeatRow> rows;
	private int freeSeats;

	private Venue() {

	}

	public static Venue getVenueInstance() {
		if (venue == null)
			venue = new Venue();

		return venue;
	}

	public List<SeatRow> getRows() {
		if (rows == null)
			rows = new ArrayList<SeatRow>();
		return rows;
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

	public void print() {
		System.out.println("");
		System.out.println("---------------- Current Venue Booking ---------------------------");
		for (SeatRow seatRow : rows) {
			for (Seat seat : seatRow.getSeats()) {
				System.out.print(seat.getStatus() + "\t");
			}
			System.out.println();
		}
		System.out.println("------------------------------------------------------------------");
	}

}
