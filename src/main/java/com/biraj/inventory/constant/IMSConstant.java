/**
 * 
 */
package com.biraj.inventory.constant;

import org.springframework.stereotype.Component;

/**
 * @author birajmishra
 * Constants used across application
 */
@Component
public interface IMSConstant {
	
	String UNABLE_TO_AUTHENTICATE = "Authentication Failed.";
	String ACCESS_TOKEN = "AccessToken";
	String AUTHORIZATION = "Authorization";
	String UUID = "uuid";
	String INVALID_REQUEST = "Invalid request, please try again with valid request.";
	String FORBIDDEN = "You don't have access to the requested resource.";
	String BAD_REQUEST = "Bad Request, Please recheck the request.";
	String OUT_OF_STOCK = "Out of Stock.";
	String INVALID_REQUEST_FOR_INGREDIENT = "Invalid request, please try again with valid ingredient.";
	String INVALID_REQUEST_FOR_PRODUCT = "Invalid request, please try again with valid product and catagory.";
	String RELOGIN = "Unauthorized Access, Please relogin.";
	
	
}
