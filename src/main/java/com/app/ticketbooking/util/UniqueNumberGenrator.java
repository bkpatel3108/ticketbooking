package com.app.ticketbooking.util;

//To be guaranteed to have unique number then we should create database
// procedure to generate unique number
public class UniqueNumberGenrator {

	static Integer uniqueNoTicketHold = 1000000000;
	static Integer uniqueNoTicketConfirm = 2000000000;

	// Returning unix timestamp
	public static Integer getUnixTimeBasedUniqueNumber() {
		return (int) (System.currentTimeMillis() / 1000L);
	}

	// Returning Unique No in Sequence
	public static Integer getTicketHoldUniqueNumber() {
		Integer currentUniqueNo = uniqueNoTicketHold++;
		return currentUniqueNo;
	}

	// Returning Unique No in Sequence
	public static Integer getTicketConfirmUniqueNumber() {
		Integer currentUniqueNo = uniqueNoTicketConfirm++;
		return currentUniqueNo;
	}
}
