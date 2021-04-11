package com.boot.booking.restaurant.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.boot.booking.restaurant.entities.Reservation;
import com.boot.booking.restaurant.exceptions.BookingException;
import com.boot.booking.restaurant.repositories.ReservationRespository;
import com.boot.booking.restaurant.services.impl.CancelReservationServiceImpl;

public class CancelReservationServiceTest {

	private static final String LOCATOR = "Burguer 7";
	private static final String RESERVATION_DELETED = "LOCATOR_DELETED";

	private static final Reservation RESERVATION = new Reservation();

	@Mock
	private ReservationRespository reservationRespository;

	@InjectMocks
	private CancelReservationServiceImpl cancelReservationServiceImpl;

	@Before
	public void init() throws BookingException {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void deleteReservationOK() throws BookingException {
		Mockito.when(this.reservationRespository.findByLocator(LOCATOR)).thenReturn(Optional.of(RESERVATION));
		Mockito.when(this.reservationRespository.deleteByLocator(LOCATOR)).thenReturn(Optional.of(RESERVATION));
		
		final String response = this.cancelReservationServiceImpl.deleteReservation(LOCATOR);
		assertEquals(response, RESERVATION_DELETED);
	}
	
	@Test(expected = BookingException.class)
	public void deleteReservationNotFountError() throws BookingException {
		Mockito.when(this.reservationRespository.findByLocator(LOCATOR)).thenReturn(Optional.empty());
		Mockito.when(this.reservationRespository.deleteByLocator(LOCATOR)).thenReturn(Optional.of(RESERVATION));
		final String response = this.cancelReservationServiceImpl.deleteReservation(LOCATOR);
		assertEquals(response, RESERVATION_DELETED);
		
		fail();
	}
	
	@Test(expected = BookingException.class)
	public void deleteReservationInternalServerError() throws BookingException {
		Mockito.when(this.reservationRespository.findByLocator(LOCATOR)).thenReturn(Optional.of(RESERVATION));
		
		Mockito.doThrow(Exception.class).when(this.reservationRespository).deleteByLocator(LOCATOR);
		
		final String response = this.cancelReservationServiceImpl.deleteReservation(LOCATOR);
		assertEquals(response, RESERVATION_DELETED);
		
		fail();
	}
}
