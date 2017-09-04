package com.app.ticketbooking.service;

import java.util.HashMap;
import java.util.Map;

public class SeatHoldAuthorizationService {

	// key-seatHoldId, value-emailId
	// customer can have multiple seat holds
	static Map<Integer, String> customerSeatHoldsMap = new HashMap<Integer, String>();

	/***
	 * 
	 * This method check if customer is authorized for given seat hold
	 * 
	 * @param emailId
	 * @param seatHoldId
	 * @return whether customer is authorized for seat hold or not
	 * 
	 */
	public static boolean authorizeUser(String emailId, Integer seatHoldId) {
		String customerEmailBySeatHoldId = customerSeatHoldsMap.get(seatHoldId);
		if (customerEmailBySeatHoldId != null && customerEmailBySeatHoldId.equals(emailId)) {
			return true;
		}
		return false;
	}

	/***
	 * 
	 * This method authorize customer for given seat hold
	 * 
	 * @param emailId
	 * @param seatHoldId
	 */
	public static void addAuthorizeUser(String emailId, Integer seatHoldId) {
		customerSeatHoldsMap.put(seatHoldId, emailId);
	}

	/***
	 * This method remove authorization of seat hold
	 * 
	 * @param seatHoldId
	 */
	public static void removeAuthorizeUser(Integer seatHoldId) {
		customerSeatHoldsMap.remove(seatHoldId);
	}
}
