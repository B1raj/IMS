package com.biraj.inventory.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.biraj.inventory.bean.AccessToken;
import com.biraj.inventory.bean.AccessTokenPayload;
import com.biraj.inventory.bean.UserInfo;
import com.biraj.inventory.bean.UserTokens;
import com.biraj.inventory.exception.AccessTokenException;
import com.biraj.inventory.exception.AuthenticationException;
import com.biraj.inventory.utils.DateUtil;
/**
 * @author birajmishra
 */
@Component
public class AccessFactory {

	private static final Logger LOG = LoggerFactory.getLogger(AccessFactory.class);

	@Autowired
	private JwtTokenService tokenUtil;

	@Autowired
	HttpServletRequest request;

	@Value("${accesstoken.issuer}")
	private String accessTokenIssuer;

	@Value("${accesstoken.audience}")
	private String audience;

	@Value("${jwt.secret}")
	private String secret;

	/**
	 * Create access token.
	 * 
	 * @param clientId
	 * @param userId
	 * @param accessOverride
	 * @param refreshOverride
	 * @return
	 * @throws AccessTokenException
	 * @throws AuthenticationException
	 * @throws TokenizerException
	 */
	public UserTokens createToken(UserInfo userInfo) {
		UserTokens response = new UserTokens();
		// fetch profile

		// prepare token payload
		AccessTokenPayload payload = computeAccessTokenPayload(userInfo, accessTokenIssuer);

		// create token
		response.setAccessToken(tokenUtil.generateAccessToken(payload, secret));

		LOG.info("AccessTokenFactory : access token geneated for  user: {} ", userInfo.getInternalUserId());
		if (LOG.isTraceEnabled()) {
			LOG.trace("AccessTokenFactory : createToken : response : " + response.toString());
		}
		return response;
	}

	public AccessToken verifyAccessToken(String accessToken) throws AccessTokenException {
		LOG.info("AccessTokenFactory : verifyAccessToken : verifying access token {}", accessToken);
		AccessToken token = tokenUtil.verifyAccessToken(accessToken, secret);
		return token;
	}

	// public AccessToken getAccessTokenObj(String accessToken) throws
	// AccessTokenException {
	// LOG.info("AccessTokenFactory : getAccessTokenObj : access token {}",
	// accessToken);
	// return tokenUtil.verifyAccessToken(accessToken, secret);
	// }

	/**
	 * create payload bean for access token.
	 *
	 * @param partyId
	 * @param iss
	 * @param access
	 * @return
	 */
	private AccessTokenPayload computeAccessTokenPayload(UserInfo userInfo, String iss) {

		AccessTokenPayload payload = new AccessTokenPayload();
		payload.setIssuer(iss);
		payload.setIssuedDate(DateUtil.currentDate());
		payload.setOutletId(userInfo.getOutletId());
		payload.setPartyId(userInfo.getInternalUserId());
		payload.setAudience(audience);

		if (LOG.isTraceEnabled()) {
			LOG.trace("AccessTokenFactory : computeAccessTokenPayload : payload : " + payload.toString());
		}
		return payload;
	}

}
