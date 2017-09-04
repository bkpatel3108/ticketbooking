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
	// b3 should receive confirmation code TimeOut#1000000003.
	// b4 should receive confirmation code TimeOut#1000000004.
	@Test
	public void ticketConfirmingAndTimeoutTest() {
		ApplicationIntializeService.intialize(10, 10, SeatSearchAlgo.ONLY_IN_SAME_ROW, 5000L, 1000L);
		TicketService ticketService = (TicketService) TicketServiceFactory.getService("TicketService");
		;
		SeatHold seatHold = ticketService.findAndHoldSeats(10, "b1");
		SeatHold seatHold1 = ticketService.findAndHoldSeats(5, "b2");
		SeatHold seatHold2 = ticketService.findAndHoldSeats(6, "b3");
		SeatHold seatHold3 = ticketService.findAndHoldSeats(5, "b4");

		assertEquals("b1 should receive confirmation code Success#1000000000", "Success#2000000000",
				ticketService.reserveSeats(seatHold.getSeatHoldId(), "b1"));
		assertEquals("b2 should receive confirmation code Success#1000000001", "Success#2000000001",
				ticketService.reserveSeats(seatHold1.getSeatHoldId(), "b2"));
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals("b3 should receive confirmation code TimeOut#1000000002", "TimeOut#1000000002",
				ticketService.reserveSeats(seatHold2.getSeatHoldId(), "b3"));
		assertEquals("b4 should receive confirmation code TimeOut#1000000003", "TimeOut#1000000003",
				ticketService.reserveSeats(seatHold3.getSeatHoldId(), "b4"));
	}
}
