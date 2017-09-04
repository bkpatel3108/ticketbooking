package com.app.ticketbooking.unit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.app.ticketbooking.enums.SeatStatus;
import com.app.ticketbooking.service.ApplicationIntializeService;
import com.app.ticketbooking.valueobject.Seat;
import com.app.ticketbooking.valueobject.SeatRow;
import com.app.ticketbooking.valueobject.Venue;

public class SeatStatusObserverTest {
	// Given : Venue is initialized with 5 rows and 5 cols. Algo is not

	// When : Status of one seat is updated to HELD

	// Then : Free seat in Venue should be decreased by 1, and Free seat in
	// SeatRow should be decreased by 1.
	@Test
	public void freeSeatCountDecreaseTest() {
		// Given : Venue is initialized with 5 rows and 5 cols. Algo is not set
		ApplicationIntializeService.configureVenue(5, 5);
		Venue venue = Venue.getVenueInstance();
		SeatRow firstSeatRow = venue.getRows().get(0);
		Seat firstSeat = firstSeatRow.getSeats().get(0);
				
		// When : Status of one seat is updated to HELD
		firstSeat.setStatus(SeatStatus.HELD);
		
		// Then : Free seat in Venue should be decreased by 1, and Free seat in
		// SeatRow should be decreased by 1.
		assertEquals("Free seat in Venue should be decreased by 1", 24,
				venue.getFreeSeats());
		assertEquals("Free seat in SeatRow should be decreased by 1", 4,
				firstSeatRow.getFreeSeats());
	}
}
