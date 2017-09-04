package com.app.ticketbooking.service;

import com.app.ticketbooking.enums.SeatSearchAlgo;
import com.app.ticketbooking.valueobject.SeatSearchVO;

/***
 * 
 * @author bhaumikpatel
 *
 */
public interface SeatSearchService {
	/***
	 * This methods find best seats for customer. It uses default algorithm –
	 * Only book in same row.
	 * 
	 * @param noOfSeats
	 * @return
	 */
	SeatSearchVO findBestMatchSeats(int noOfSeats);

	/***
	 * 
	 * This method is used to set algorithm to find best seats for customer.
	 * Right now, only default algorithm is supported
	 * 
	 * @param algorithm
	 */
	void setAlgorithm(SeatSearchAlgo algorithm);
}