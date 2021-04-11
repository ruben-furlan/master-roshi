package com.boot.booking.restaurant.entities;

import javax.persistence.CascadeType;
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
@Table(name = "BOARD")
public class Board {

    private Long id;
    private Long capacity;
    private Long number;

    private Restaurant restaurant;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    @Column(name = "CAPACITY")
    public Long getCapacity() {
        return this.capacity;
    }

    @Column(name = "NUMBER")
    public Long getNumber() {
        return this.number;
    }

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "RESTAURANT_ID", nullable = false)
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
