package com.boot.booking.restaurant.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.boot.booking.restaurant.exceptions.BookingException;
import com.boot.booking.restaurant.exceptions.NotFountException;
import com.boot.booking.restaurant.repositories.RestaurantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.booking.restaurant.entities.Restaurant;
import com.boot.booking.restaurant.jsons.RestaurantRest;
import com.boot.booking.restaurant.services.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
    RestaurantRepository restaurantRepository;

	public static final ModelMapper modelMapper = new ModelMapper();

	public RestaurantRest getRestaurantById(Long restaurantId) throws BookingException {
		return this.modelMapper.map(getRestaurantEntity(restaurantId), RestaurantRest.class);
	}

	public List<RestaurantRest> getRestaurants() throws BookingException {
		final List<Restaurant> restaurantsEntity = this.restaurantRepository.findAll();
		return restaurantsEntity
				.stream()
				.map(service -> this.modelMapper.map(service, RestaurantRest.class))
				.collect(Collectors.toList());
	}

	private Restaurant getRestaurantEntity(Long restaurantId) throws BookingException {
		return this.restaurantRepository.findById(restaurantId).orElseThrow(() -> new NotFountException("SNOT-404-1", "RESTAURANT_NOT_FOUND"));
	}

}
