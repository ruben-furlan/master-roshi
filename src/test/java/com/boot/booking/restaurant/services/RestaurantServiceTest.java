package com.boot.booking.restaurant.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.boot.booking.restaurant.entities.Board;
import com.boot.booking.restaurant.entities.Reservation;
import com.boot.booking.restaurant.entities.Restaurant;
import com.boot.booking.restaurant.entities.Turn;
import com.boot.booking.restaurant.exceptions.BookingException;
import com.boot.booking.restaurant.jsons.RestaurantRest;
import com.boot.booking.restaurant.repositories.RestaurantRepository;
import com.boot.booking.restaurant.services.impl.RestaurantServiceImpl;

public class RestaurantServiceTest {

	private static final Long RESTAURANT_ID = 1L;
	private static final String NAME = "Buger";
	private static final String DESCRIPTION = "Todo tipo de hamburguesas";
	private static final String ADDRES = "Av. Galindo";
	private static final String IMAGE = "www.image.com";

	public static final Restaurant RESTAURANT = new Restaurant();
	public static final List<Turn> TURN_LIST = new ArrayList<>();
	public static final List<Board> BOARD_LIST = new ArrayList<>();
	public static final List<Reservation> RESERVATION_LIST = new ArrayList<>();

	@Mock
	RestaurantRepository restaurantRepository;

	@InjectMocks
	RestaurantServiceImpl restaurantService;

	@Before
	public void init() throws BookingException {
		MockitoAnnotations.initMocks(this);

		RESTAURANT.setName(NAME);
		RESTAURANT.setDescription(DESCRIPTION);
		RESTAURANT.setAddress(ADDRES);
		RESTAURANT.setId(RESTAURANT_ID);
		RESTAURANT.setImage(IMAGE);
		RESTAURANT.setTurns(TURN_LIST);
		RESTAURANT.setBoards(BOARD_LIST);
		RESTAURANT.setReservations(RESERVATION_LIST);

	}

	@Test
	public void getRestaurantByIdTest() throws BookingException {
		Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.of(RESTAURANT));
		restaurantService.getRestaurantById(RESTAURANT_ID);
	}
	
	@Test(expected = BookingException.class)
	public void getRestaurantByIdTestError() throws BookingException {
		Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.empty());
		restaurantService.getRestaurantById(RESTAURANT_ID);
	fail();
		
	}
	
	@Test 
	public void getRestaurantsTest() throws BookingException {
		final Restaurant restaurant =new  Restaurant();
		Mockito.when(restaurantRepository.findAll()).thenReturn(Arrays.asList(restaurant));	
		final List<RestaurantRest> response = restaurantService.getRestaurants();
		assertNotNull(response);
		assertFalse(response.isEmpty());
		assertEquals(response.size(), 1);
		
	}

}





