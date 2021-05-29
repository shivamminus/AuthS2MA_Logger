package com.authorize.main.exception;

public class LoginException extends Exception {

	
    private static final long serialVersionUID = 1L;


	/* 
	 * Passing Exception to Exception Constructor 
	 */
    public LoginException(String exceptionMessage) {
        super(exceptionMessage);
	}
    
}
