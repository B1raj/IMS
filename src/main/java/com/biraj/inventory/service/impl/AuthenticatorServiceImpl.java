package com.biraj.inventory.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biraj.inventory.bean.AuthenticatorResponse;
import com.biraj.inventory.bean.UserInfo;
import com.biraj.inventory.constant.IMSConstant;
import com.biraj.inventory.constant.IMSErrorCodeConstant;
import com.biraj.inventory.entity.User;
import com.biraj.inventory.exception.AuthenticationException;
import com.biraj.inventory.exception.BadRequestException;
import com.biraj.inventory.repository.UserRepository;
import com.biraj.inventory.service.AuthenticatorService;
import com.biraj.inventory.utils.CrypticUtil;

/**
 * @author birajmishra
 */

@Service
public class AuthenticatorServiceImpl implements AuthenticatorService {

	@Autowired
	UserRepository userRepository;

	private static final Logger LOG = LoggerFactory.getLogger(AuthenticatorServiceImpl.class);

	@Override
	public AuthenticatorResponse authenticate(String authorization)
			throws AuthenticationException, BadRequestException {
		if (LOG.isTraceEnabled()) {
			LOG.trace("AuthenticatorServiceImpl : authenticate : Start");
		}
		String[] authParts = authorization.split("\\s+");
		if (null != authParts && authParts.length != 2) {
			throw new BadRequestException(IMSErrorCodeConstant.BAD_REQUEST, IMSConstant.BAD_REQUEST);
		}
		String authInfo = authParts[1];
		String decodedAuth = CrypticUtil.decrypt(authInfo);
		AuthenticatorResponse response = null;

		String userid = decodedAuth.split(":")[0];
		String password = decodedAuth.split(":")[1];

		User user = userRepository.findByUserid(userid);

		if (user != null && userid.equals(user.getUserid()) && password.equals(user.getPassword())) {
			// valid user
			response = new AuthenticatorResponse();
			response.setAuthenticated(true);
			UserInfo userInfo = new UserInfo(user.getName(), user.getOutletId());
			response.setUserInfo(userInfo);
			if (LOG.isTraceEnabled()) {
				LOG.trace("****LOGIN SUCCESSFUL **** for user{}", userid);
			}
		} else {
			// invalid user
			LOG.error("Authentication failed for provided {}", userid);
			throw new AuthenticationException(IMSErrorCodeConstant.UNABLE_TO_AUTHENTICATE,
					IMSConstant.UNABLE_TO_AUTHENTICATE);
		}
		return response;

	}

}
