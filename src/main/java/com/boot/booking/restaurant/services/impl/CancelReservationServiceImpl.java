package com.boot.booking.restaurant.services.impl;

import com.boot.booking.restaurant.exceptions.BookingException;
import com.boot.booking.restaurant.exceptions.NotFountException;
import com.boot.booking.restaurant.repositories.ReservationRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.booking.restaurant.exceptions.InternalServerErrorException;
import com.boot.booking.restaurant.services.CancelReservationService;

@Service
public class CancelReservationServiceImpl implements CancelReservationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CancelReservationServiceImpl.class);

	@Autowired
	private ReservationRespository reservationRespository;

	public String deleteReservation(String locator) throws BookingException {

		this.reservationRespository.findByLocator(locator).orElseThrow(() -> new NotFountException("LOCATOR_NOT_FOUND", "LOCATOR_NOT_FOUND"));

		try {
			this.reservationRespository.deleteByLocator(locator);
		} catch (Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR", e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}

		return "LOCATOR_DELETED";
	}

}
