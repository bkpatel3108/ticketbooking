package com.app.ticketbooking.service;

import java.util.List;

import com.app.ticketbooking.backgroundservice.SeatHoldExpireBackgroundSevice;
import com.app.ticketbooking.enums.SeatSearchAlgo;
import com.app.ticketbooking.servicelocator.TicketServiceFactory;
import com.app.ticketbooking.valueobject.Seat;
import com.app.ticketbooking.valueobject.SeatRow;
import com.app.ticketbooking.valueobject.Venue;

public class ApplicationIntializeService {

	/**
	 * 
	 * Basic initialization
	 * 
	 * @param noOfRows
	 *            Number of rows in Venue
	 * @param noOfSeatsInRow
	 *            Number of seats in each row of Venue
	 */
	public static void intialize(int noOfRows, int noOfSeatsInRow) {
		intialize(noOfRows, noOfSeatsInRow, SeatSearchAlgo.ONLY_IN_SAME_ROW, null, null);
	}

	/***
	 * 
	 * Advance initialization This method initialize ticket service application
	 * with given inputs
	 * 
	 * @param noOfRows
	 *            Number of rows in Venue
	 * @param noOfSeatsInRow
	 *            Number of seats in each row of Venue
	 * @param seatSearchAlgo
	 *            Algorithm to find best seats for customer
	 * @param seatHoldExpireTime
	 *            Ticket hold expire time
	 * @param interval
	 *            Time interval for background service to recheck for expired
	 *            tickets
	 */
	public static void intialize(int noOfRows, int noOfSeatsInRow, SeatSearchAlgo seatSearchAlgo,
			Long seatHoldExpireTime, Long interval) {

		Thread backgroundSeviceThread;

		// Configure Venue
		configureVenue(noOfRows, noOfSeatsInRow);

		// Configure Seat Search Algo
		SeatSearchService seatSearchService = (SeatSearchService) TicketServiceFactory.getService("SeatSearchService");
		seatSearchService.setAlgorithm(seatSearchAlgo);

		// Configure Background Service
		if (seatHoldExpireTime != null && interval != null) {
			backgroundSeviceThread = new Thread(new SeatHoldExpireBackgroundSevice(seatHoldExpireTime, interval));
		} else {
			backgroundSeviceThread = new Thread(new SeatHoldExpireBackgroundSevice());
		}
		backgroundSeviceThread.start();
	}

	// Venue configuration
	public static void configureVenue(int noOfRows, int noOfSeatsInRow) {
		Venue venue = Venue.getVenueInstance();
		venue.setFreeSeats(noOfRows * noOfSeatsInRow);
		List<SeatRow> seatRows = venue.getRows();
		seatRows.clear();
		for (int i = 0; i < noOfRows; i++) {
			SeatRow seatRow = new SeatRow(i + 1);
			List<Seat> seats = seatRow.getSeats();
			seats.clear();
			for (int j = 0; j < noOfSeatsInRow; j++) {
				Seat seat = new Seat(seatRow, j + 1);
				seats.add(seat);
			}
			seatRow.setFreeSeats(noOfSeatsInRow);
			seatRows.add(seatRow);
		}
	}

	// Not Supported
	// Advance configuration
	static void configureVenue(int noOfGenRows, int noOfPremiumRows, int noOfSeatsInRow) {

	}
}
