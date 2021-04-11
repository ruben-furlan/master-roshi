package com.boot.booking.restaurant.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.boot.booking.restaurant.controllers.RestaurantController;
import com.boot.booking.restaurant.exceptions.BookingException;
import com.boot.booking.restaurant.jsons.RestaurantRest;
import com.boot.booking.restaurant.jsons.TurnRest;
import com.boot.booking.restaurant.responses.BookingResponse;
import com.boot.booking.restaurant.responses.MessageEnum;
import com.boot.booking.restaurant.services.RestaurantService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

public class RestaurantControllerTest {

	private static final Long RESTAURANT_ID = 1L;
	private static final String NAME = "Buger";
	private static final String DESCRIPTION = "Todo tipo de hamburguesas";
	private static final String ADDRES = "Av. Galindo";
	private static final String IMAGE = "www.image.com";

	private static final String SUCCES_STATUS = "Succes";
	private static final HttpStatus SUCCES_CODE = HttpStatus.OK;
	private static final MessageEnum OK = MessageEnum.Ok;

	public static final List<TurnRest> TURN_LIST = new ArrayList<>();
	public static final RestaurantRest RESTAURANT_REST = new RestaurantRest();
	public static final List<RestaurantRest> RESTAURANT_REST_LIST = new ArrayList<>();

	@Mock
	private RestaurantService restaurantService;

	@InjectMocks
	private RestaurantController restaurantController;

	@Before
	public void init() throws BookingException {
		MockitoAnnotations.initMocks(this);

		RESTAURANT_REST.setName(NAME);
		RESTAURANT_REST.setDescription(DESCRIPTION);
		RESTAURANT_REST.setAddress(ADDRES);
		RESTAURANT_REST.setId(RESTAURANT_ID);
		RESTAURANT_REST.setImage(IMAGE);
		RESTAURANT_REST.setTurns(TURN_LIST);

		Mockito.when(this.restaurantService.getRestaurantById(RESTAURANT_ID)).thenReturn(RESTAURANT_REST);
	}

	@Test
	public void getRestaurantByIdTest() throws BookingException {
		final BookingResponse<RestaurantRest> response = this.restaurantController.getRestaurantById(RESTAURANT_ID);

		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), RESTAURANT_REST);

	}

	@Test
	public void getRestaurantsTest() throws BookingException {
		final BookingResponse<List<RestaurantRest>> response = this.restaurantController.getRestaurants();

		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), RESTAURANT_REST_LIST);

	}

}
