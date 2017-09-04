package com.app.ticketbooking.service;

import java.util.List;

import com.app.ticketbooking.enums.SeatStatus;
import com.app.ticketbooking.valueobject.Seat;

public class SeatUpdateServiceImpl implements SeatUpdateService {

	private static SeatUpdateService seatUpdateService;

	private SeatUpdateServiceImpl() {

	}

	public static SeatUpdateService getInstance() {
		if (seatUpdateService == null)
			seatUpdateService = new SeatUpdateServiceImpl();
		return seatUpdateService;
	}

	// change it to return error code
	public synchronized boolean changeSeatsStatus(List<Seat> seats, SeatStatus oldStatus, SeatStatus newStatus) {

		// double check before changing status
		for (Seat seat : seats) {
			if (seat.getStatus() != oldStatus) {
				return false;
				// return error code
			}
		}

		for (Seat seat : seats) {
			seat.setStatus(newStatus);
		}

		return true;
	}

	public void setSeatConfirmationCode(List<Seat> seats, String confirmationCode) {
		for (Seat seat : seats) {
			seat.setSeatConfirmationId(confirmationCode);
		}

	}

}
