package com.app.ticketbooking;

import java.util.Scanner;

import com.app.ticketbooking.enums.SeatSearchAlgo;
import com.app.ticketbooking.service.ApplicationIntializeService;
import com.app.ticketbooking.service.TicketService;
import com.app.ticketbooking.servicelocator.TicketServiceFactory;
import com.app.ticketbooking.valueobject.SeatHold;
import com.app.ticketbooking.valueobject.Venue;

/**
 * Ticket Booking Main App
 *
 */
public class App {
	public static void main(String[] args) {
		// test1();

		Scanner reader = null;
		while (true) {
			try {
				reader = new Scanner(System.in);
				System.out.println("\n----- Ticket Service Configuration -----");
				System.out.println("1 - For Default \n2 - For Manual \n0 - To Quit");
				System.out.print("Enter a number: ");
				int configType = reader.nextInt();
				if (configType == 0) {
					System.exit(0);
				} else if (configType == 1) {
					ApplicationIntializeService.intialize(10, 10);
					System.out.println("\n----- Default Configuration -----");
					System.out.println("Number of rows in venue : 10");
					System.out.println("Number of seats in each row : 10");
					System.out.println("Best Seat Search Configuration : Book only in same row");
					System.out.println("Ticket hold expire time (in seconds) : 30");
					System.out.println(
							"Time interval for background service to recheck for expired tickets (in seconds) : 2");

				} else if (configType == 2) {
					System.out.println("\n----- Venue Configuration -----");

					System.out.print("Please enter number of rows in venue : ");
					Integer noOfRows = reader.nextInt();

					System.out.print("\nPlease enter number of seats in each row : ");
					Integer noOfSeats = reader.nextInt();

					System.out.println("\n----- Best Seat Search Configuration -----");

					System.out.println(
							"\n1 - Book only in same Row \n2 - Book max in Same Row(Coming Soon) \n3 - Divide seats in equal Rows(Coming Soon)");
					System.out.print("Enter a number: ");
					SeatSearchAlgo seatSearchAlgo = SeatSearchAlgo.ONLY_IN_SAME_ROW;
					int searchAlgo = reader.nextInt();
					if (searchAlgo == 1) {
						seatSearchAlgo = SeatSearchAlgo.values()[searchAlgo - 1];
					} else {
						System.out.println(
								"Only \"1 - Book only in same Row\" is supported. Selecting \"1 - Book only in same Row\"");
					}
					System.out.println("\n----- Ticket/Seat Hold Timeout Configuration -----");

					System.out.print("\nPlease enter ticket hold expire time (in seconds) : ");
					Long seatHoldExpireTime = Long.valueOf(reader.nextInt() * 1000);

					System.out.print(
							"\nPlease enter time interval for background service to recheck for expired tickets (in seconds) : ");
					Long interval = Long.valueOf(reader.nextInt() * 1000);
					ApplicationIntializeService.intialize(noOfRows, noOfSeats, seatSearchAlgo, seatHoldExpireTime,
							interval);
				} else {
					throw new Exception("Invalid Command");
				}

				TicketService ticketService = (TicketService) TicketServiceFactory.getService("TicketService");
				while (true) {
					try {
						System.out.println("\n");
						System.out.println("---------- Ticket Booking Console ----------");
						System.out.println("1 - To check no of seats available");
						System.out.println("2 - To find and hold best seats");
						System.out.println("3 - To confirm hold seats");
						System.out.println("4 - To print venue chart");
						System.out.println("0 - To quit");
						System.out.print("\nPlease Enter a number : ");
						Integer command = reader.nextInt();
						if (command == 0) {
							System.exit(0);
						} else if (command == 1) {
							System.out.println("\nNo of seats available : " + ticketService.numSeatsAvailable());
						} else if (command == 2) {
							System.out.print("\nPlease enter number of seats to find and hold : ");
							Integer noOfSeatsToHold = reader.nextInt();
							System.out.print("\nPlease enter email id of customer : ");
							String customerEmail = reader.next();
							SeatHold seatHold = ticketService.findAndHoldSeats(noOfSeatsToHold, customerEmail);
							System.out.println("\n----- Ticket/Seat Hold Details -----");
							seatHold.print();
						} else if (command == 3) {
							System.out.print("\nPlease enter seat hold id : ");
							Integer seatHoldId = reader.nextInt();
							System.out.print("\nPlease enter email id of customer :");
							String customerEmail = reader.next();
							String confirmationCode = ticketService.reserveSeats(seatHoldId, customerEmail);
							System.out.println("\n----- Ticket Reservation Confirmation Code -----");
							System.out.println("Confirmation code : " + confirmationCode);
						} else if (command == 4) {
							ticketService.printVenueChart();
						} else {
							System.out.println("\nInvalid Command");
							reader.nextLine();
						}
					} catch (Exception e) {
						System.out.println("\nInvalid Command. Please enter appropriate values and try again.");
					} finally {
						if (reader != null)
							reader.nextLine();
					}
				}
			} catch (Exception e) {
				System.out.println("\nInvalid Command. Please enter appropriate values and try again.");
			} finally {
				if (reader != null)
					reader.nextLine();
			}
		}

	}

	static void test1() {
		ApplicationIntializeService.intialize(10, 10);
		TicketService ticketService = (TicketService) TicketServiceFactory.getService("TicketService");
		;
		SeatHold seatHold = ticketService.findAndHoldSeats(10, "b1");
		SeatHold seatHold1 = ticketService.findAndHoldSeats(5, "b2");
		SeatHold seatHold2 = ticketService.findAndHoldSeats(6, "b3");
		SeatHold seatHold3 = ticketService.findAndHoldSeats(5, "b4");
		seatHold.print();
		Venue.getVenueInstance().print();
		ticketService.reserveSeats(seatHold.getSeatHoldId(), "b1");
		ticketService.reserveSeats(seatHold1.getSeatHoldId(), "b2");
		Venue.getVenueInstance().print();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ticketService.reserveSeats(seatHold2.getSeatHoldId(), "b3");
		ticketService.reserveSeats(seatHold3.getSeatHoldId(), "b4");
		Venue.getVenueInstance().print();
	}
}
