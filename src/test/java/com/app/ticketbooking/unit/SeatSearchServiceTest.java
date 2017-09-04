package com.app.ticketbooking.unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.app.ticketbooking.enums.SeatStatus;
import com.app.ticketbooking.service.ApplicationIntializeService;
import com.app.ticketbooking.service.SeatSearchService;
import com.app.ticketbooking.service.SeatUpdateService;
import com.app.ticketbooking.servicelocator.TicketServiceFactory;
import com.app.ticketbooking.valueobject.Seat;
import com.app.ticketbooking.valueobject.SeatSearchVO;

public class SeatSearchServiceTest {

	// Given : Venue is initialized with 5 rows and 5 cols. Algo is not
	// set(Default algo ONLY_IN_SAME_ROW). Venue has 4 booked tickets in 1st row

	// When : findBestMatchSeats method is called to hold 4 seats.

	// Then : first 4 seats of 2nd row should be returned.
	@Test
	public void onlyInSameRowAlgoTest() {
		// Given : Venue is initialized with 5 rows and 5 cols. Algo is not set
		ApplicationIntializeService.configureVenue(5, 5);
		SeatSearchService seatSearchService = (SeatSearchService) TicketServiceFactory.getService("SeatSearchService");
		SeatSearchVO seatSearchVO1 = seatSearchService.findBestMatchSeats(4);
		List<Seat> bestSeats1 = seatSearchVO1.getSeats();
		SeatUpdateService seatStatusService = (SeatUpdateService) TicketServiceFactory.getService("SeatUpdateService");
		seatStatusService.changeSeatsStatus(bestSeats1, SeatStatus.FREE, SeatStatus.HELD);

		// When : findBestMatchSeats method is called to hold 4 seats.
		SeatSearchVO seatSearchVO2 = seatSearchService.findBestMatchSeats(4);

		// Then : first 4 seats of 2nd row should be returned.
		List<Seat> bestSeats2 = seatSearchVO2.getSeats();
		List<String> bestSeatsStr2 = new ArrayList<String>();
		for (Seat seat : bestSeats2) {
			bestSeatsStr2.add(seat.getSeatId());
		}
		assertThat(bestSeatsStr2, containsInAnyOrder("2-1", "2-2", "2-3", "2-4"));
	}

}
