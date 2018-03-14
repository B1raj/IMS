package com.biraj.inventory.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.biraj.inventory.bean.UserInfo;
import com.biraj.inventory.bean.UserTokens;
import com.biraj.inventory.exception.AccessTokenException;
import com.biraj.inventory.exception.AuthenticationException;
import com.biraj.inventory.service.impl.AccessFactory;
import com.biraj.inventory.service.impl.JwtTokenService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({ "in-memory" })
public class TestAccessTokenFactory {

	@Autowired
	private AccessFactory tokenFactory;

	@Autowired
	JwtTokenService tokenUtil;


	@Test
	public void testCreate() throws AccessTokenException, AuthenticationException {
		UserInfo userInfo = new UserInfo("1123",12);
		UserTokens token = tokenFactory.createToken(userInfo);
		assertNotNull(token);
		assertNotNull(token.getAccessToken());

	}

	@Test(expected = AccessTokenException.class)
	public void testVerifyTokenInvalid() throws AccessTokenException {
		tokenFactory.verifyAccessToken(
				"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.Jpc3MiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20iLCJpYXQiOjE1MTA5ODA1ODIsInN1YiI6IkJpcmFqIiwiYXVkIjoiaHR0cHM6Ly9iaXJhai5pbnZlbnRvcnkuY29tL2FjY2VzcyJ9.BttFVuieUEc2dlCIHnJJsKFKz5yYqvrs4SDbPOpGofk");
	}

}
