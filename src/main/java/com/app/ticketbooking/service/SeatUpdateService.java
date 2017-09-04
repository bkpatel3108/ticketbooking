package com.app.ticketbooking.service;

import java.util.List;

import com.app.ticketbooking.enums.SeatStatus;
import com.app.ticketbooking.valueobject.Seat;

/***
 * 
 * @author bhaumikpatel
 *
 */
public interface SeatUpdateService {

	/***
	 * 
	 * Method to update status of seats. It will first check given seats are
	 * currently in old status, and if they are in old status (double check),
	 * then it will update them with new status.
	 * 
	 * 
	 * @param seats
	 * @param oldStatus
	 * @param newStatus
	 * @return
	 */
	boolean changeSeatsStatus(List<Seat> seats, SeatStatus oldStatus, SeatStatus newStatus);

	/***
	 * 
	 * Method to add confirmation id to seat
	 * 
	 * @param seats
	 * @param confirmationCode
	 */
	void setSeatConfirmationCode(List<Seat> seats, String confirmationCode);
}
