package com.boot.bookingrestaurantapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.responses.BookingResponse;
import com.boot.bookingrestaurantapi.services.CancelReservationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/booking-restaurant/v1")
public class CancelReservationController {

	@Autowired
	private CancelReservationService cancelReservationService;

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "/deleteReservation",produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingResponse<String> deleteReservation(@RequestParam String locator) throws BookingException {
		return new BookingResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK", this.cancelReservationService.deleteReservation(locator));
	}

}
