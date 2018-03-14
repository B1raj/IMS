/**
 * 
 */
package com.biraj.inventory.bean;

import java.io.Serializable;

/**
 * @author birajmishra
 *To be used as an input for authenticator.
 */
public class AuthenticatorResponse implements Serializable {

	@Override
	public String toString() {
		return "AuthenticatorResponse [authenticated=" + authenticated + ", userInfo=" + userInfo + "]";
	}

	private static final long serialVersionUID = 1L;

	private boolean authenticated;
	private UserInfo userInfo;
	

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}




}
