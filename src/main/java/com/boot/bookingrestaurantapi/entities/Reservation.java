package com.boot.bookingrestaurantapi.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RESERVATION")
public class Reservation {

	private Long id;
	private String locator;
	private String turn;
	private Long person;
	private Date date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESTAURANT_ID", nullable = false)
	private Restaurant restaurant;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	@Column(name = "LOCATOR")
	public String getLocator() {
		return this.locator;
	}
	@Column(name = "TURN")
	public String getTurn() {
		return this.turn;
	}

	@Column(name = "PERSON")
	public Long getPerson() {
		return this.person;
	}

	@Column(name = "DATE")
	public Date getDate() {
		return this.date;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLocator(String locator) {
		this.locator = locator;
	}

	public void setTurn(String turn) {
		this.turn = turn;
	}

	public void setPerson(Long person) {
		this.person = person;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
}
