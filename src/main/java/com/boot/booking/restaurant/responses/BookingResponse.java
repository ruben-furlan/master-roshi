package com.boot.booking.restaurant.responses;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class BookingResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String status;
	private HttpStatus code;
	private MessageEnum message;
	private T data;
	
	public BookingResponse(String status, HttpStatus code, MessageEnum message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}
	
	public BookingResponse(String status, HttpStatus code, MessageEnum message, T data) {
		this.status = status;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public HttpStatus getCode() {
		return this.code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}

	public MessageEnum getMessage() {
		return this.message;
	}

	public void setMessage(MessageEnum message) {
		this.message = message;
	}

	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
	

}
