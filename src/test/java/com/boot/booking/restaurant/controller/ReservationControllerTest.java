package com.boot.booking.restaurant.controller;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import com.boot.booking.restaurant.controllers.ReservationController;
import com.boot.booking.restaurant.exceptions.BookingException;
import com.boot.booking.restaurant.jsons.CreateReservationRest;
import com.boot.booking.restaurant.responses.BookingResponse;
import com.boot.booking.restaurant.responses.MessageEnum;
import com.boot.booking.restaurant.services.ReservationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

public class ReservationControllerTest {
	
	
	private static final String SUCCES_STATUS = "Succes";
	private static final HttpStatus SUCCES_CODE = HttpStatus.OK;
	private static final MessageEnum OK = MessageEnum.Ok;
	
	private static final Long RESTAURANT_ID = 1L;
	private static final Date DATE  = new Date();
	private static final Long PERSON = 1L;
	private static final Long TURN_ID = 1L;
	private static final String LOCATOR = "BURGER 2";
	
	private CreateReservationRest CREATE_RESERVATION_REST = new CreateReservationRest();
	
	@Mock
    private ReservationService reservationService;
	
	
	@InjectMocks
	private ReservationController reservationController;
	
	
	@Before
	public void init() throws BookingException {
		MockitoAnnotations.initMocks(this);
		
		CREATE_RESERVATION_REST.setDate(DATE);
		CREATE_RESERVATION_REST.setPerson(PERSON);
		CREATE_RESERVATION_REST.setRestaurantId(RESTAURANT_ID);
		CREATE_RESERVATION_REST.setTurnId(TURN_ID);
		
		Mockito.when(this.reservationService.createReservation(CREATE_RESERVATION_REST)).thenReturn(LOCATOR);
		
	}
	
	@Test
	public void createReservationTest() throws BookingException{
	
		BookingResponse<String> response = this.reservationController.createReservation(CREATE_RESERVATION_REST);
		
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), LOCATOR);
		
	}

}







