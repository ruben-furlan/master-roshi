package com.boot.booking.restaurant.exceptions;

import java.util.ArrayList;
import java.util.List;

import com.boot.booking.restaurant.dtos.ErrorDto;

public class BookingException extends Exception{
	
	private static final long serialVersionUID = 1L;

	private final String code;
	
	private final int responseCode;
	
	private final List<ErrorDto> errorList = new ArrayList<>();

	public BookingException(String code, int responseCode, String message) {
		super(message);
		this.code = code;
		this.responseCode = responseCode;
	}
	
	public BookingException(String code, int responseCode, String message, List<ErrorDto> errorList) {
		super(message);
		this.code = code;
		this.responseCode = responseCode;
		this.errorList.addAll(errorList);
	}

	public String getCode() {
		return this.code;
	}

	public int getResponseCode() {
		return this.responseCode;
	}

	public List<ErrorDto> getErrorList() {
		return this.errorList;
	}
	
	

}
