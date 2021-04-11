package com.boot.booking.restaurant.controllers;

import javax.validation.Valid;

import com.boot.booking.restaurant.responses.MessageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.boot.booking.restaurant.exceptions.BookingException;
import com.boot.booking.restaurant.jsons.CreateReservationRest;
import com.boot.booking.restaurant.jsons.ReservationRest;
import com.boot.booking.restaurant.responses.BookingResponse;
import com.boot.booking.restaurant.services.ReservationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/booking-restaurant/v1")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "reservation/{reservationId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingResponse<ReservationRest> getReservationById(@PathVariable Long reservationId) throws BookingException {
		return new BookingResponse<>("Succes", HttpStatus.OK, MessageEnum.Ok, reservationService.getReservation(reservationId));
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "reservation", produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingResponse<String> createReservation(@RequestBody @Valid CreateReservationRest createReservationRest) throws BookingException {
		return new BookingResponse<>("Succes", HttpStatus.OK, MessageEnum.Ok, this.reservationService.createReservation(createReservationRest));
	}

}
