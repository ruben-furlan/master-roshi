package com.boot.booking.restaurant.services;

import com.boot.booking.restaurant.exceptions.BookingException;

public interface CancelReservationService {
	
	public String deleteReservation(String locator) throws BookingException;

}
