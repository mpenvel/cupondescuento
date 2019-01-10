package com.mrjeff.cupondescuento.exceptions;

import org.springframework.http.HttpStatus;

public class CuponDescuentoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus status;

	public CuponDescuentoException(String message) {
		super(message);
		this.status = HttpStatus.INTERNAL_SERVER_ERROR;
	}

	public CuponDescuentoException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
