package com.biraj.inventory.exception;

public class AuthenticationException extends IMSException {

	/**
	 * @author birajmishra
	 */
	private static final long serialVersionUID = 1L;
	
	public AuthenticationException() {
		super("40002" , "Authentication exception occured.");
	}
	
	public AuthenticationException(String errorCode,String errorDescription) {
		super(errorCode , errorDescription);
	}
	
	public AuthenticationException(String errorCode,String errorDescription , Exception exception) {
		super(errorCode , errorDescription , exception);
	}
	
	
}
