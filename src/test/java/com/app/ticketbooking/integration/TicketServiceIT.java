package com.app.ticketbooking.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.app.ticketbooking.enums.SeatSearchAlgo;
import com.app.ticketbooking.service.ApplicationIntializeService;
import com.app.ticketbooking.service.TicketService;
import com.app.ticketbooking.servicelocator.TicketServiceFactory;
import com.app.ticketbooking.valueobject.SeatHold;

public class TicketServiceIT {

	// Given :
	// Venue is initialized with 10 rows and 10 cols.
	// Algo is not set (Default algo ONLY_IN_SAME_ROW).
	// Default ticket hold time is 5 seconds.

	// When :
	// b1 calls findAndHoldSeats method to hold 10 seats.
	// b2 calls findAndHoldSeats method to hold 5 seats.
	// b3 calls findAndHoldSeats method to hold 6 seats.
	// b4 calls findAndHoldSeats method to hold 5 seats.
	// b1 and b2 calls reserveSeats method immediately.
	// b2 and b4 calls reserveSeats method after 10 seconds.

	// Then :
	// b1 should receive confirmation code Success#2000000000.
	// b2 should receive confirmation code Success#2000000001.
	// b3 should receive confirmation code TimeOut#seatHoldIdB3.
	// b4 should receive confirmation code TimeOut#seatHoldIdB4.
	@Test
	public void ticketConfirmingAndTimeoutTest() {
		ApplicationIntializeService.intialize(10, 10, SeatSearchAlgo.ONLY_IN_SAME_ROW, 5000L, 1000L);
		TicketService ticketService = (TicketService) TicketServiceFactory.getService("TicketService");

		SeatHold seatHoldB1 = ticketService.findAndHoldSeats(10, "b1");
		SeatHold seatHoldB2 = ticketService.findAndHoldSeats(5, "b2");
		SeatHold seatHoldB3 = ticketService.findAndHoldSeats(6, "b3");
		SeatHold seatHoldB4 = ticketService.findAndHoldSeats(5, "b4");

		Integer seatHoldIdB3 = seatHoldB3.getSeatHoldId();
		Integer seatHoldIdB4 = seatHoldB4.getSeatHoldId();

		assertEquals("b1 should receive confirmation code Success#seatHoldId1", "Success#2000000000",
				ticketService.reserveSeats(seatHoldB1.getSeatHoldId(), "b1"));
		assertEquals("b2 should receive confirmation code Success#1000000001", "Success#2000000001",
				ticketService.reserveSeats(seatHoldB2.getSeatHoldId(), "b2"));
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals("b3 should receive confirmation code TimeOut#seatHoldId3", "TimeOut#" + seatHoldIdB3,
				ticketService.reserveSeats(seatHoldB3.getSeatHoldId(), "b3"));
		assertEquals("b4 should receive confirmation code TimeOut#seatHoldId4", "TimeOut#" + seatHoldIdB4,
				ticketService.reserveSeats(seatHoldB4.getSeatHoldId(), "b4"));
	}

	// Given :
	// Venue is initialized with 10 rows and 10 cols.
	// Algo is not set (Default algo ONLY_IN_SAME_ROW).
	// Default ticket hold time is 5 seconds.
	// User has hold 10 seats for email : b1.

	// When :
	// User try to confirm ticket with invalid user email

	// Then :
	// User should receive confirmation code UnauthorizedAccess#seatHoldId.
	@Test
	public void ticketConfirmingInvalidEmailNegativeTest() {

		// Given :
		// Venue is initialized with 10 rows and 10 cols.
		// Algo is not set (Default algo ONLY_IN_SAME_ROW).
		// Default ticket hold time is 5 seconds.
		// User has hold 10 seats for email : b1.
		ApplicationIntializeService.intialize(10, 10, SeatSearchAlgo.ONLY_IN_SAME_ROW, 5000L, 1000L);
		TicketService ticketService = (TicketService) TicketServiceFactory.getService("TicketService");
		SeatHold seatHold = ticketService.findAndHoldSeats(10, "b1");
		Integer seatHoldId = seatHold.getSeatHoldId();

		// When :
		// User try to confirm ticket with invalid user email
		String confirmationMessage = ticketService.reserveSeats(seatHold.getSeatHoldId(), "b2");

		// Then :
		// User should receive confirmation code UnauthorizedAccess#seatHoldId.
		assertEquals("User should receive confirmation code as UnauthorizedAccess#seatHoldId",
				"UnauthorizedAccess#" + seatHoldId, confirmationMessage);

	}
}
