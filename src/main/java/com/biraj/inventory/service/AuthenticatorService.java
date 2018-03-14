package com.biraj.inventory.service;

import com.biraj.inventory.bean.AuthenticatorResponse;
import com.biraj.inventory.exception.AuthenticationException;
import com.biraj.inventory.exception.BadRequestException;

/**
 * @author birajmishra
 */
public interface AuthenticatorService {

	/**
	 * @param authRequest
	 * @return
	 */
	AuthenticatorResponse authenticate(String authorization) throws AuthenticationException, BadRequestException;

}
