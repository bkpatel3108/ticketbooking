package com.app.ticketbooking.service;

import java.util.List;

import com.app.ticketbooking.valueobject.Seat;

/***
 * 
 * @author bhaumikpatel
 *
 */
public interface SeatHoldSevice {

	/***
	 * 
	 * It add list of seats against seat hold id.
	 * 
	 * @param seatHoldId
	 *            seat/ticket hold id
	 * @param seats
	 *            List of seats
	 */
	public void addSeatHold(Integer seatHoldId, List<Seat> seats);

	/***
	 * 
	 * @param seatHoldId
	 * @return
	 */
	public List<Seat> getSeatHold(Integer seatHoldId);

	/***
	 * 
	 * @return size of seat/ticket hold
	 */
	public Integer seatHoldSize();

	/***
	 * 
	 * It makes held seats free if their hold time is expired
	 * 
	 * @param seatHoldExpireTime
	 */
	public void removeExpiredTicketHold(Long seatHoldExpireTime);
}
