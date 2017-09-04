package com.app.ticketbooking.test;

import com.app.ticketbooking.service.ApplicationIntializeService;
import com.app.ticketbooking.service.TicketService;
import com.app.ticketbooking.service.TicketServiceImpl;
import com.app.ticketbooking.servicelocator.TicketServiceFactory;
import com.app.ticketbooking.valueobject.SeatHold;
import com.app.ticketbooking.valueobject.Venue;

public class TicketServiceTest {
	
	
	public static void main(String[] args){
		test1();
	}
	
	static void test1(){
		ApplicationIntializeService.intialize(10,10);
		TicketService ticketService = (TicketService) TicketServiceFactory.getService("TicketService");;
		SeatHold seatHold = ticketService.findAndHoldSeats(10,"b1");
		SeatHold seatHold1 = ticketService.findAndHoldSeats(5,"b2");
		SeatHold seatHold2 = ticketService.findAndHoldSeats(6,"b3");
		SeatHold seatHold3 = ticketService.findAndHoldSeats(5,"b4");
		seatHold.print();
		Venue.getVenueInstance().print();
		System.out.println(ticketService.reserveSeats(seatHold.getSeatHoldId(), "b1"));
		System.out.println(ticketService.reserveSeats(seatHold1.getSeatHoldId(), "b2"));
		Venue.getVenueInstance().print();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ticketService.reserveSeats(seatHold.getSeatHoldId(), "b1");
		//ticketService.reserveSeats(seatHold1.getSeatHoldId(), "b2");
		System.out.println(ticketService.reserveSeats(seatHold2.getSeatHoldId(), "b3"));
		System.out.println(ticketService.reserveSeats(seatHold3.getSeatHoldId(), "b4"));
		Venue.getVenueInstance().print();
	}
	
}
