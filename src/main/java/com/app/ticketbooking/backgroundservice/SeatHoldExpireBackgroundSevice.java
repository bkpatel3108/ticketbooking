package com.app.ticketbooking.backgroundservice;

import com.app.ticketbooking.service.SeatHoldSevice;
import com.app.ticketbooking.servicelocator.TicketServiceFactory;

/***
 * This service runs on separate thread, and check for expired ticket after
 * configured interval time.
 * 
 * Ticket considered expired if they are not booked within configured Ticket
 * Hold Time.
 * 
 * @author bhaumikpatel
 *
 */
public class SeatHoldExpireBackgroundSevice implements Runnable {
	// in milliseconds
	private Long seatHoldExpireTime;
	private Long interval;
	SeatHoldSevice seatHoldService;

	public SeatHoldExpireBackgroundSevice() {
		// In real time application ticketHoldExpireTime will be 5 minutes and
		// interval will be 1 minute or 30 seconds. For testing this application
		// quickly we are using seconds instead of minutes
		this.seatHoldExpireTime = 30000L;
		this.interval = 2000L;
		seatHoldService = (SeatHoldSevice) TicketServiceFactory.getService("SeatHoldSevice");
	}

	public SeatHoldExpireBackgroundSevice(Long ticketHoldExpireTime, Long interval) {
		this.seatHoldExpireTime = ticketHoldExpireTime;
		this.interval = interval;
		seatHoldService = (SeatHoldSevice) TicketServiceFactory.getService("SeatHoldSevice");
	}

	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(interval);
				while (seatHoldService.seatHoldSize() > 0) {
					seatHoldService.removeExpiredTicketHold(seatHoldExpireTime);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
