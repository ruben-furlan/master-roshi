package com.boot.booking.restaurant.services;

import com.boot.booking.restaurant.exceptions.BookingException;
import com.boot.booking.restaurant.jsons.CreateReservationRest;
import com.boot.booking.restaurant.jsons.ReservationRest;

public interface ReservationService {
	
	ReservationRest getReservation(Long reservationId) throws BookingException;
	
	String createReservation(CreateReservationRest CreateReservationRest) throws BookingException;

}
