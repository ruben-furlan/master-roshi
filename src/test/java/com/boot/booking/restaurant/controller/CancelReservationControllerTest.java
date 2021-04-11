package com.boot.booking.restaurant.controller;

import static org.junit.Assert.assertEquals;

import com.boot.booking.restaurant.controllers.CancelReservationController;
import com.boot.booking.restaurant.exceptions.BookingException;
import com.boot.booking.restaurant.responses.BookingResponse;
import com.boot.booking.restaurant.responses.MessageEnum;
import com.boot.booking.restaurant.services.CancelReservationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

public class CancelReservationControllerTest {
	
	private static final String SUCCES_STATUS = "Succes";
	private static final HttpStatus SUCCES_CODE = HttpStatus.OK;
	private static final MessageEnum OK = MessageEnum.Ok;
	
	
	
	private static final String RESERVATION_DELETED = "LOCATOR_DELETED";
	private static final String LOCATOR = "Burguer 7";

	@Mock
	private CancelReservationService cancelReservationService;

	@InjectMocks
	private CancelReservationController cancelReservationController;
	
	@Before
	public void init() throws BookingException {
		MockitoAnnotations.initMocks(this);
		
		Mockito.when(this.cancelReservationService.deleteReservation(LOCATOR)).thenReturn(RESERVATION_DELETED);
		
	}
	
	
	@Test
	public void deleteReservationTest() throws BookingException{
		
		final BookingResponse<String> response = this.cancelReservationController.deleteReservation(LOCATOR);
		
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), RESERVATION_DELETED);
		
	}

}
