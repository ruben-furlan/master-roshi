package com.boot.booking.restaurant.jsons;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateReservationRest {
	
	@JsonProperty("date")
	private Date date;
	
	@JsonProperty("person")
	private Long person;
	
	@JsonProperty("turnId")
	private Long turnId;
	
	@JsonProperty("restaurantId")
	private Long restaurantId;

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getPerson() {
		return this.person;
	}

	public void setPerson(Long person) {
		this.person = person;
	}

	public Long getTurnId() {
		return this.turnId;
	}

	public void setTurnId(Long turnId) {
		this.turnId = turnId;
	}

	public Long getRestaurantId() {
		return this.restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}
	

}
