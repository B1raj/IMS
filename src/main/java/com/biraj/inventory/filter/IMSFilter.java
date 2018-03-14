package com.biraj.inventory.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.biraj.inventory.bean.AccessToken;
import com.biraj.inventory.bean.ErrorInfo;
import com.biraj.inventory.constant.IMSConstant;
import com.biraj.inventory.constant.IMSErrorCodeConstant;
import com.biraj.inventory.service.impl.AccessFactory;

/**
 * 
 * @author birajmishra
 * Global filter for all requestion , for secuity.
 */
@ComponentScan
@Component
public class IMSFilter implements Filter {

	private static final Logger LOG = LoggerFactory.getLogger(IMSFilter.class);

	/**
	 * AccessTokenFactory.
	 */
	 @Autowired
	 private AccessFactory tokenFactory;
	
	 
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		MDC.put("requestUUID", req.getHeader(IMSConstant.UUID));
		String accessToken = req.getHeader(IMSConstant.ACCESS_TOKEN);
		String authorization = req.getHeader(IMSConstant.AUTHORIZATION);
		Boolean isChainingDisabled = false;
		ErrorInfo errorInfo = null;
		
		if (authorization != null && !"".equals(authorization.trim())) {
			isChainingDisabled = false;
		}else if (accessToken != null && !"".equals(accessToken.trim())) {
			AccessToken accessTokenObj = null;
			try {
				accessTokenObj = tokenFactory.verifyAccessToken(accessToken);
				req.setAttribute(IMSConstant.ACCESS_TOKEN, accessTokenObj);
			}catch(Exception e){
				LOG.error(e.getMessage(), e);
				LOG.error("IMSFilter : doFilter : invalid access Token : {} ", accessToken);
				errorInfo = new ErrorInfo(IMSErrorCodeConstant.ACCESS_TOKEN_INVALID,IMSConstant.RELOGIN);
				res.setStatus(HttpStatus.UNAUTHORIZED.value()); 
				res.setContentType("application/json");
				res.getWriter().write(errorInfo.toString());
				isChainingDisabled = true;
			}
		
			if (LOG.isTraceEnabled() && null !=accessTokenObj) {
				LOG.trace("IMSFilter : doFilter : accessTokenObj : " + accessTokenObj.toString());
			}
		}
		
		if(!isChainingDisabled){
			chain.doFilter(req, res);		
		}
	}

	@Override
	public void destroy() {

	}

}
