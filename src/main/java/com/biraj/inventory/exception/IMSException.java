package com.biraj.inventory.exception;

/**
 * 
 * @author birajmishra
 * IMS Global Exception handler
 */

public abstract class IMSException extends RuntimeException  {
	private static final long serialVersionUID = -5103834241136923370L;
	
	private String errorCode;
	
	public IMSException(String errorCode, String errorDescription) {
		super(errorDescription);
		this.errorCode = errorCode;
	}
	
	public IMSException(String errorCode, String errorDescription , Exception exception) {
		super(errorDescription , exception);
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return errorCode;
		
	}
	
}
