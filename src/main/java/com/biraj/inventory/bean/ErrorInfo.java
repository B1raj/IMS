package com.biraj.inventory.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * @author birajmishra
 *Defines the structure of error message to be returned.
 */

public class ErrorInfo implements Serializable {

	
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String errorMessage;
	
	public ErrorInfo() {
		super();
	}

	public ErrorInfo(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	
	}

	public String getErrorCode() {
		return errorCode;
	}

	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	
	@Override
	public String toString() {
		return "{ \"errorCode\":\"" + errorCode + "\", \"errorMessage\":\"" + errorMessage + "\"}";	}
	
	
}
