package com.app.ticketbooking.service;

import java.util.HashMap;
import java.util.Map;

public class SeatHoldAuthorizationService {

	// key-seatHoldId, value-emailId
	// customer can have multiple seat holds
	static Map<Integer,String> customerSeatHoldsMap = new HashMap<Integer,String>();

	public static boolean authorizeUser(String emailId, Integer seatHoldId) {
		String customerEmailBySeatHoldId = customerSeatHoldsMap.get(seatHoldId);
		if (customerEmailBySeatHoldId != null && customerEmailBySeatHoldId.equals(emailId)) {
			return true;
		}
		return false;
	}

	public static void addAuthorizeUser(String emailId, Integer seatHoldId) {
		customerSeatHoldsMap.put(seatHoldId,emailId);
	}

	public static void removeAuthorizeUser(Integer seatHoldId) {
		customerSeatHoldsMap.remove(seatHoldId);
	}
}
