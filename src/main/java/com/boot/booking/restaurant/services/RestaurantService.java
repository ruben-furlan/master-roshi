package com.boot.booking.restaurant.services;

import java.util.List;

import com.boot.booking.restaurant.exceptions.BookingException;
import com.boot.booking.restaurant.jsons.RestaurantRest;

public interface RestaurantService {
	
	RestaurantRest getRestaurantById(Long restaurantId) throws BookingException;
	
	 List<RestaurantRest> getRestaurants() throws BookingException;

}
