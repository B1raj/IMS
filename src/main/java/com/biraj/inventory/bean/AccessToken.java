package com.biraj.inventory.bean;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author birajmishra
 * Access token class defining structure of token, which secures API's
 */
@Component
public class AccessToken {

	@Override
	public String toString() {
		return "AccessToken [headers=" + headers + ", payload=" + payload + "]";
	}

	private Map<String, Object> headers;
	private AccessTokenPayload payload;
	
	@JsonIgnore
	private static final Logger LOG = LoggerFactory.getLogger(AccessToken.class);
	
	public Map<String, Object> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, Object> headers) {
		this.headers = headers;
	}

	public AccessTokenPayload getPayload() {
		return payload;
	}

	public void setPayload(AccessTokenPayload payload) {
		this.payload = payload;
	}

	

	
}
