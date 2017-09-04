package com.app.ticketbooking.valueobject;

import java.util.ArrayList;
import java.util.List;

import com.app.ticketbooking.enums.SeatStatus;
import com.app.ticketbooking.listner.SeatStatusObserver;

public class Seat {

	private String seatId;
	private int seatNo;
	// to track which customer has reserved this seat(Reservation Code)
	private String seatConfirmationId;
	private SeatStatus status;
	private List<SeatStatusObserver> seatStatusObservers;
	
	/***
	 * 
	 * @param seatRow
	 * @param seatNo
	 */
	public Seat(SeatRow seatRow, int seatNo) {
		this.seatNo = seatNo;
		this.status = SeatStatus.FREE;
		this.seatId = seatRow.getRowId() + "-" + seatNo;
		seatStatusObservers = new ArrayList<SeatStatusObserver>();
		seatStatusObservers.add(seatRow);
		seatStatusObservers.add(Venue.getVenueInstance());
	}

	public String getSeatId() {
		return seatId;
	}

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int colId) {
		this.seatNo = colId;
	}

	public String getSeatConfirmationId() {
		return seatConfirmationId;
	}

	public void setSeatConfirmationId(String seatConfirmationId) {
		this.seatConfirmationId = seatConfirmationId;
	}

	public SeatStatus getStatus() {
		return status;
	}
	
	/***
	 * 
	 * @param status
	 */
	public void setStatus(SeatStatus status) {
		if (status == SeatStatus.FREE) {
			if (this.status != SeatStatus.FREE) {
				for (SeatStatusObserver seatStatusObserver : seatStatusObservers)
					seatStatusObserver.incrementFreeSeatCount();
			}
		} else {
			if (this.status == SeatStatus.FREE) {
				for (SeatStatusObserver seatStatusObserver : seatStatusObservers)
					seatStatusObserver.decrementFreeSeatCount();
			}
		}
		this.status = status;
	}

}
