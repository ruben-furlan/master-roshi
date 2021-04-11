package com.boot.booking.restaurant.controllers;

import java.util.List;

import com.boot.booking.restaurant.exceptions.BookingException;
import com.boot.booking.restaurant.responses.MessageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.boot.booking.restaurant.jsons.RestaurantRest;
import com.boot.booking.restaurant.responses.BookingResponse;
import com.boot.booking.restaurant.services.RestaurantService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/booking-restaurant/v1")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "restaurant/{restaurantId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingResponse<RestaurantRest> getRestaurantById(@PathVariable Long restaurantId) throws BookingException {
		return new BookingResponse<>("Succes", HttpStatus.OK, MessageEnum.Ok, this.restaurantService.getRestaurantById(restaurantId));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "restaurants", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingResponse<List<RestaurantRest>> getRestaurants() throws BookingException {
		return new BookingResponse<>("Succes", HttpStatus.OK, MessageEnum.Ok, this.restaurantService.getRestaurants());
	}

}
