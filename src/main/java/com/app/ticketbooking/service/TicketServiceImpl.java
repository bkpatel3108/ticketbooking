package com.app.ticketbooking.service;

import java.util.List;

import com.app.ticketbooking.enums.TicketHoldStatus;
import com.app.ticketbooking.enums.SeatStatus;
import com.app.ticketbooking.servicelocator.TicketServiceFactory;
import com.app.ticketbooking.util.UniqueNumberGenrator;
import com.app.ticketbooking.valueobject.ApplicationConstants;
import com.app.ticketbooking.valueobject.Seat;
import com.app.ticketbooking.valueobject.SeatHold;
import com.app.ticketbooking.valueobject.SeatSearchVO;
import com.app.ticketbooking.valueobject.Venue;

public class TicketServiceImpl implements TicketService {

	private static TicketService ticketService;
	SeatSearchService seatSearchService;
	SeatUpdateService seatUpdateService;
	SeatHoldSevice seatHoldService;

	public static TicketService getInstance() {
		if (ticketService == null)
			ticketService = new TicketServiceImpl();
		return ticketService;
	}

	private TicketServiceImpl() {
		seatSearchService = (SeatSearchService) TicketServiceFactory.getService("SeatSearchService");
		seatUpdateService = (SeatUpdateService) TicketServiceFactory.getService("SeatUpdateService");
		seatHoldService = (SeatHoldSevice) TicketServiceFactory.getService("SeatHoldSevice");
	}

	public int numSeatsAvailable() {
		return Venue.getVenueInstance().getFreeSeats();
	}

	public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
		SeatHold seatHold = new SeatHold();
		seatHold.setEmailId(customerEmail);
		synchronized (this) {
			SeatSearchVO seatSearchVO = seatSearchService.findBestMatchSeats(numSeats);
			List<Seat> bestSeats = seatSearchVO.getSeats();
			if (bestSeats != null && bestSeats.size() > 0) {
				boolean status = seatUpdateService.changeSeatsStatus(bestSeats, SeatStatus.FREE, SeatStatus.HELD);
				if (status == true) {
					Integer seatHoldId = UniqueNumberGenrator.getTicketHoldUniqueNumber();
					seatHold.setSeatHoldId(seatHoldId);
					SeatHoldAuthorizationService.addAuthorizeUser(customerEmail, seatHoldId);
					seatHoldService.addSeatHold(seatHoldId, bestSeats);
					// Not exposing Seat Object to end user
					// seatHoldSeatsMap.put(seatHoldId, bestSeats);
					List<String> seatIds = seatHold.getSeatIds();
					for (Seat seat : bestSeats) {
						seatIds.add(seat.getSeatId());
					}
					seatHold.setSeatHoldStatus(TicketHoldStatus.HELD);

				} else {
					seatHold.setSeatHoldStatus(TicketHoldStatus.FAILED);
				}
			} else {
				seatHold.setSeatHoldStatus(TicketHoldStatus.FAILED);
				seatHold.setErrorMessage(seatSearchVO.getErrorMessage());
			}
		}

		return seatHold;

	}

	public String reserveSeats(int seatHoldId, String customerEmail) {
		boolean isAutorized = SeatHoldAuthorizationService.authorizeUser(customerEmail, seatHoldId);
		if (isAutorized == true) {
			synchronized (this) {
				List<Seat> seats = seatHoldService.getSeatHold(seatHoldId);
				if (seats != null && seats.size() > 0) {
					// If seats are already confirmed then just return
					// confirmation code again
					if (seats.get(0).getStatus().equals(SeatStatus.RESV)) {
						// Success
						return seats.get(0).getSeatConfirmationId();
					} else {
						boolean isUpdateSeatStatus = seatUpdateService.changeSeatsStatus(seats, SeatStatus.HELD,
								SeatStatus.RESV);
						if (isUpdateSeatStatus == true) {
							Integer seatConfirmId = UniqueNumberGenrator.getTicketConfirmUniqueNumber();
							String confirmationCode = ApplicationConstants.SUCCESS + "#" + seatConfirmId;
							seatUpdateService.setSeatConfirmationCode(seats, confirmationCode);
							// Success
							return confirmationCode;
						} else {
							// Timeout
							return ApplicationConstants.TIME_OUT + "#" + seatHoldId;
						}
					}
				}
			}
		} else {
			// Unauthorized access
			return ApplicationConstants.UNAUTHORIZED_ACCESS + "#" + seatHoldId;
		}
		//Error
		return ApplicationConstants.ERROR + "#" + seatHoldId;
	}

	public void printVenueChart() {
		Venue.getVenueInstance().print();
	}
}
