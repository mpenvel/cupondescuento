package com.mrjeff.cupondescuento.exceptions.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mrjeff.cupondescuento.exceptions.CuponDescuentoException;

@ControllerAdvice
public class CuponDescuentoHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { CuponDescuentoException.class })
	protected ResponseEntity<String> handleConflict(CuponDescuentoException e) {
		CuponDescuentoException mktEx = (CuponDescuentoException) e;
		
		return new ResponseEntity<>(e.getMessage(), mktEx.getStatus());
	}
}
