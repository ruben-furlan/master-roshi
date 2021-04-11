package com.boot.booking.restaurant.services.impl;

import com.boot.booking.restaurant.exceptions.BookingException;
import com.boot.booking.restaurant.exceptions.NotFountException;
import com.boot.booking.restaurant.repositories.ReservationRespository;
import com.boot.booking.restaurant.repositories.RestaurantRepository;
import com.boot.booking.restaurant.repositories.TurnRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.booking.restaurant.entities.Reservation;
import com.boot.booking.restaurant.entities.Restaurant;
import com.boot.booking.restaurant.entities.Turn;
import com.boot.booking.restaurant.exceptions.InternalServerErrorException;
import com.boot.booking.restaurant.jsons.CreateReservationRest;
import com.boot.booking.restaurant.jsons.ReservationRest;
import com.boot.booking.restaurant.services.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private TurnRepository turnRepository;

	@Autowired
	private ReservationRespository reservationRepository;

	public static final ModelMapper modelMapper = new ModelMapper();

	public ReservationRest getReservation(Long reservationId) throws BookingException {
		return this.modelMapper.map(getReservationEntity(reservationId), ReservationRest.class);
	}

	public String createReservation(final CreateReservationRest createReservationRest) throws BookingException {

		final Restaurant restaurantId = this.restaurantRepository.findById(createReservationRest.getRestaurantId())
				.orElseThrow(() -> new NotFountException("RESTAURANT_NOT_FOUND", "RESTAURANT_NOT_FOUND"));

		final Turn turn = this.turnRepository.findById(createReservationRest.getTurnId())
				.orElseThrow(() -> new NotFountException("TURN_NOT_FOUND", "TURN_NOT_FOUND"));

		boolean present = this.reservationRepository.findByTurnAndRestaurantId(turn.getName(), restaurantId.getId()).isPresent();

		if (present) {
			throw new NotFountException("RESERVATION_ALREADT_EXIST", "RESERVATION_ALREADT_EXIST");
		}

		String locator = this.generateLocator(restaurantId, createReservationRest);

		final Reservation reservation = new Reservation();
		reservation.setLocator(locator);
		reservation.setPerson(createReservationRest.getPerson());
		reservation.setDate(createReservationRest.getDate());
		reservation.setRestaurant(restaurantId);
		reservation.setTurn(turn.getName());

		try {
			this.reservationRepository.save(reservation);
		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR", e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return locator;
	}

	private String generateLocator(final Restaurant restaurantId, final CreateReservationRest createReservationRest) throws BookingException {
		return restaurantId.getName() + createReservationRest.getTurnId();
	}

	private Reservation getReservationEntity(Long reservationId) throws BookingException {
		return reservationRepository.findById(reservationId)
				.orElseThrow(() -> new NotFountException("SNOT-404-1", "RESERVATION_NOT_FOUND"));
	}
}
