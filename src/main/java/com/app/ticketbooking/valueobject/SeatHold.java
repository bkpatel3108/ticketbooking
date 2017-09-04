package com.app.ticketbooking.valueobject;

import java.util.ArrayList;
import java.util.List;

import com.app.ticketbooking.enums.TicketHoldStatus;

public class SeatHold {

	Integer seatHoldId;
	String emailId;
	List<String> seatIds;
	TicketHoldStatus seatHoldStatus;
	// If status is failed
	String errorMessage;

	public Integer getSeatHoldId() {
		return seatHoldId;
	}

	public void setSeatHoldId(Integer seatHoldId) {
		this.seatHoldId = seatHoldId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public List<String> getSeatIds() {
		if (seatIds == null)
			seatIds = new ArrayList<String>();
		return seatIds;
	}

	public TicketHoldStatus getSeatHoldStatus() {
		return seatHoldStatus;
	}

	public void setSeatHoldStatus(TicketHoldStatus seatHoldStatus) {
		this.seatHoldStatus = seatHoldStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setSeatIds(List<String> seatIds) {
		this.seatIds = seatIds;
	}

	public void print() {
		System.out.println("Seat Hold Id : " + seatHoldId);
		System.out.println("Customr email id : " + emailId);
		System.out.println("Seat hold status : " + seatHoldStatus);
		System.out.println("Error message : " + errorMessage);
		if (seatIds != null && !seatIds.isEmpty()) {
			for (String seatId : seatIds) {
				System.out.print(seatId + "\t");
			}
		}
	}

}
