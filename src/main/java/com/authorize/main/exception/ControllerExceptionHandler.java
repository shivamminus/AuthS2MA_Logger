package com.authorize.main.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.authorize.main.dto.ErrorMessage;


@ControllerAdvice
public class ControllerExceptionHandler {

	/*
	 * Exception Method for Invalid Credentials
	*/	
	@ExceptionHandler(value = { LoginException.class})
	public ResponseEntity<ErrorMessage> loginExceptionHandel(Exception ex, WebRequest request) {
		final Date date = new Date();
		ErrorMessage message = new ErrorMessage(401, date, ex.getMessage());

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.UNAUTHORIZED);
	}
	
	
	/*
	* Exception for Improper Parameters passed 
	*/
	@ExceptionHandler(value = { LoginCredentialNotValid.class, Exception.class })
	public ResponseEntity<ErrorMessage> invalidLoginCredentialHandel(Exception ex, WebRequest request) {
		final Date date = new Date();
		ErrorMessage message = new ErrorMessage(400, date, ex.getMessage());

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	}
}