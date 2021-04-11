package com.boot.booking.restaurant.controllers;

import com.boot.booking.restaurant.exceptions.BookingException;
import com.boot.booking.restaurant.responses.BookingResponse;
import com.boot.booking.restaurant.responses.MessageEnum;
import com.boot.booking.restaurant.services.CancelReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/booking-restaurant/v1")
public class CancelReservationController {

	@Autowired
	private CancelReservationService cancelReservationService;

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "/deleteReservation",produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingResponse<String> deleteReservation(@RequestParam String locator) throws BookingException {
		return new BookingResponse<>("Succes", HttpStatus.OK, MessageEnum.Ok, this.cancelReservationService.deleteReservation(locator));
	}

}
