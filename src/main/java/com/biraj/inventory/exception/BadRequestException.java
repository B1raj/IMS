package com.biraj.inventory.exception;

/**
 * @author birajmishra
 *This exception must be thrown if any discrepancy in the input data
 *or client did not send the complete data
 *as expected by the service.
 *
 */
public class BadRequestException extends IMSException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2733779060228501637L;
		
	public BadRequestException() {
		super("40000" , "Internal error occured.");
	}
	
	public BadRequestException(String errorCode,String errorDescription) {
		super(errorCode , errorDescription);
	}

}
