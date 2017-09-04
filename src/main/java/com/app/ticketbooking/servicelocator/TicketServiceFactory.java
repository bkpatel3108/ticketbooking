package com.app.ticketbooking.servicelocator;

import com.app.ticketbooking.service.SeatHoldSeviceImpl;
import com.app.ticketbooking.service.SeatSearchServiceImpl;
import com.app.ticketbooking.service.SeatUpdateServiceImpl;
import com.app.ticketbooking.service.TicketServiceImpl;

//Service Class factory. Can be extended to common Service class 
//and in that case return type can be Service instead of Object
//In Spring Based Application, this will be done by bean injection
public class TicketServiceFactory {

	public static Object getService(String service) {
		if ("TicketService".equals(service)) {
			return TicketServiceImpl.getInstance();
		} else if ("SeatHoldSevice".equals(service)) {
			return SeatHoldSeviceImpl.getInstance();
		} else if ("SeatUpdateService".equals(service)) {
			return SeatUpdateServiceImpl.getInstance();
		} else if ("SeatSearchService".equals(service)) {
			return SeatSearchServiceImpl.getInstance();
		}
		return null;
	}
}
