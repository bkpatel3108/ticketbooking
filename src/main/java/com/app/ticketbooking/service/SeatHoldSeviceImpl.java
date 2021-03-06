package com.app.ticketbooking.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.app.ticketbooking.enums.SeatStatus;
import com.app.ticketbooking.servicelocator.TicketServiceFactory;
import com.app.ticketbooking.valueobject.Seat;

public class SeatHoldSeviceImpl implements SeatHoldSevice {
	public static SeatHoldSevice seatHoldSevice;
	// Used for timeout seatHolds
	private Map<Integer, Long> seatHoldTimeMap;
	// Used to find Seats Objects from SeatHoldId
	private Map<Integer, List<Seat>> seatHoldSeatsMap;
	private SeatUpdateService seatUpdateService;

	private SeatHoldSeviceImpl() {
		seatHoldTimeMap = new HashMap<Integer, Long>();
		seatUpdateService = (SeatUpdateService) TicketServiceFactory.getService("SeatUpdateService");
		seatHoldSeatsMap = new HashMap<Integer, List<Seat>>();
	}

	public static SeatHoldSevice getInstance() {
		if (seatHoldSevice == null)
			seatHoldSevice = new SeatHoldSeviceImpl();
		return seatHoldSevice;
	}

	public void addSeatHold(Integer seatHoldId, List<Seat> seats) {
		// locking on map object to make sure map concurrent exception access
		// will not happen
		synchronized (seatHoldTimeMap) {
			seatHoldTimeMap.put(seatHoldId, System.currentTimeMillis());
		}
		// locking on map object to make sure map concurrent exception access
		// will not happen
		synchronized (seatHoldSeatsMap) {
			seatHoldSeatsMap.put(seatHoldId, seats);
		}
	}

	public List<Seat> getSeatHold(Integer seatHoldId) {
		return seatHoldSeatsMap.get(seatHoldId);
	}

	public Integer seatHoldSize() {
		return seatHoldTimeMap.size();
	}

	/***
	 * 
	 * It makes held seats free if their hold time is expired
	 * 
	 * @param seatHoldExpireTime
	 */
	public void removeExpiredTicketHold(Long seatHoldExpireTime) {
		Long currentTime = System.currentTimeMillis();
		// locking on map object to make sure map concurrent exception access
		// will not happen
		synchronized (seatHoldTimeMap) {
			Iterator seatHoldTimeMapItr = seatHoldTimeMap.entrySet().iterator();
			// Loop through all active seat/ticket holds
			while (seatHoldTimeMapItr.hasNext()) {
				Entry<Integer, Long> seatHoldTimeMapEntry = (Entry<Integer, Long>) seatHoldTimeMapItr.next();
				if (seatHoldTimeMapEntry != null) {
					Long holdTime = seatHoldTimeMapEntry.getValue();
					// to check seat/ticket hold is expired or not
					if (holdTime + seatHoldExpireTime < currentTime) {
						Integer seatHoldId = seatHoldTimeMapEntry.getKey();
						seatHoldTimeMapItr.remove();
						List<Seat> seats = seatHoldSeatsMap.get(seatHoldId);
						// Make seats free of this seat/ticket hold
						seatUpdateService.changeSeatsStatus(seats, SeatStatus.HELD, SeatStatus.FREE);
					}
				}
			}
		}
	}
}
