package com.biraj.inventory.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author birajmishra
 *Response of login 
 */
@Component
@Scope("prototype")
public class LoginResponse {
																		

	private String accessToken;
									

	@Override
	public String toString() {
		return "LoginPartyResponse [accessToken=" + accessToken + "]";
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	}
