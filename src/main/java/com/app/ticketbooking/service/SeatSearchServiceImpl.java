package com.app.ticketbooking.service;

import java.util.ArrayList;
import java.util.List;

import com.app.ticketbooking.enums.SeatSearchAlgo;
import com.app.ticketbooking.valueobject.ApplicationConstants;
import com.app.ticketbooking.valueobject.Seat;
import com.app.ticketbooking.valueobject.SeatRow;
import com.app.ticketbooking.valueobject.SeatSearchVO;
import com.app.ticketbooking.valueobject.Venue;

public class SeatSearchServiceImpl implements SeatSearchService {

	private static SeatSearchService seatSearchService;
	Venue venue = Venue.getVenueInstance();

	// This is most common scenario
	SeatSearchAlgo algorithm = SeatSearchAlgo.ONLY_IN_SAME_ROW;

	private SeatSearchServiceImpl() {

	}

	public static SeatSearchService getInstance() {
		if (seatSearchService == null)
			seatSearchService = new SeatSearchServiceImpl();
		return seatSearchService;
	}

	public SeatSearchAlgo getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(SeatSearchAlgo algorithm) {
		this.algorithm = algorithm;
	}

	public synchronized SeatSearchVO findBestMatchSeats(int noOfSeats) {
		List<Seat> bestSeats;
		String errorMessage = "";
		if (algorithm == SeatSearchAlgo.ONLY_IN_SAME_ROW) {
			bestSeats = findSeatsInSameRow(noOfSeats);
			if (bestSeats == null || bestSeats.isEmpty()) {
				errorMessage = ApplicationConstants.CAN_NOT_BOOK_IN_SAME_ROW;
			}
			SeatSearchVO seatSearchVO = new SeatSearchVO(bestSeats, errorMessage);
			return seatSearchVO;
		}

		// No algo selected
		return null;
	}

	// Try to assign all seats in same row. if not then not hold
	// Assigning seats from left to right in each seat row
	private List<Seat> findSeatsInSameRow(int noOfSeats) {
		// TODO Auto-generated method stub
		int seatCnt = 0;
		List<Seat> bestFreeSeats = new ArrayList<Seat>();
		List<SeatRow> seatRows = venue.getRows();
		for (int i = 0; i < seatRows.size() && seatCnt < noOfSeats; i++) {
			// if single row found where we can book all the tickets
			SeatRow seatrow = seatRows.get(i);
			if (seatrow.getFreeSeats() >= noOfSeats) {
				List<Seat> seats = seatrow.getSeats();
				int seatStartIndex = seats.size() - seatrow.getFreeSeats();
				for (int j = seatStartIndex; j < seats.size() && seatCnt < noOfSeats; j++) {
					Seat seat = seats.get(j);
					bestFreeSeats.add(seat);
					seatCnt++;
				}
				return bestFreeSeats;
			}
		}
		return bestFreeSeats;
	}

}
