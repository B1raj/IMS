/**
 * 
 */
package com.biraj.inventory.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author birajmishra
 *
 */
@Component
@Scope("prototype")
public class UserTokens {

	private String accessToken;


	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken
	 *            the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}


	@Override
	public String toString() {
		return "UserTokens [accessToken=" + accessToken + "]";
	}

}
