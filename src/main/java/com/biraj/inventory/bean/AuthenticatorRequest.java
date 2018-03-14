/**
 * 
 */
package com.biraj.inventory.bean;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author birajmishra
 *To be used as an input for authenticator.
 */
@Component
@Scope(scopeName="prototype")
public class AuthenticatorRequest {

	@Override
	public String toString() {
		return "AuthenticatorRequest [userCredentials=" + userCredentials + "]";
	}

	private Map<String, String> userCredentials;

	public Map<String, String> getUserCredentials() {
		return userCredentials;
	}

	public void setUserCredentials(Map<String, String> userCredentials) {
		this.userCredentials = userCredentials;
	}

	
}
