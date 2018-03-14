package com.biraj.inventory.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.biraj.inventory.bean.AccessToken;
import com.biraj.inventory.bean.AccessTokenPayload;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtHandlerAdapter;
/**
 * @author birajmishra
 */
@Component
public class JwtHandler extends JwtHandlerAdapter<AccessToken> {
	
	private static final Logger LOG = LoggerFactory.getLogger(JwtHandler.class);
	
	protected static final String PARTY_ID = "ptyid";
	
	@Override
	public AccessToken onClaimsJws(Jws<Claims> jws) {
		if(LOG.isTraceEnabled()){
			LOG.trace("AccessTokenJwtHandler : onClaimsJws : start ");
		}
		AccessToken accessToken = new AccessToken();
		accessToken.setHeaders(jws.getHeader());
		AccessTokenPayload payload =  new AccessTokenPayload();
		payload.setIssuer(jws.getBody().getIssuer());
		payload.setIssuedDate(jws.getBody().getIssuedAt());
		payload.setPartyId((String)jws.getBody().getSubject());
		payload.setAudience((String)jws.getBody().getAudience());
		payload.setOutletId((Integer)jws.getBody().get("outlet"));
		
		accessToken.setPayload(payload);
		if(LOG.isTraceEnabled()){
			LOG.trace("AccessTokenJwtHandler : onClaimsJws : accessToken : "+ accessToken);
		}
		
		return accessToken;
	}
	
}