/**
 * 
 */
package com.biraj.inventory.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.biraj.inventory.bean.ErrorInfo;
import com.biraj.inventory.constant.IMSErrorCodeConstant;
import com.biraj.inventory.exception.AccessTokenException;
import com.biraj.inventory.exception.AuthenticationException;
import com.biraj.inventory.exception.BadRequestException;
import com.biraj.inventory.exception.InsufficientStockException;
import com.biraj.inventory.exception.InvalidDataException;

/**
 * @author biraj
 *
 */
@ControllerAdvice(basePackages = { "com.biraj.inventory.controller" })
public class IMSControllerAdvice {

	private static final Logger LOG = LoggerFactory.getLogger(IMSControllerAdvice.class);

	/**
	 * Handler for AccessTokenException.
	 *
	 * @param req
	 * @param ex
	 * @return
	 */

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(AccessTokenException.class)
	@ResponseBody
	ErrorInfo handleAccessTokenException(HttpServletRequest req, AccessTokenException exception) {
		LOG.error("Forbidden", exception);
		return createErrorResponse(req, exception.getMessage(), exception.getErrorCode());
	}

	/**
	 * Handler for AuthenticationException.
	 *
	 * @param req
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(AuthenticationException.class)
	@ResponseBody
	ErrorInfo handleAuthenticationException(HttpServletRequest req, AuthenticationException exception) {
		LOG.error("UNAUTHORIZED_ACCESS_ERROR exception ", exception);
		return createErrorResponse(req, exception.getMessage(), exception.getErrorCode());
	}

	/**
	 * Handler for InvalidDataException.
	 *
	 * @param req
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(InvalidDataException.class)
	@ResponseBody
	ErrorInfo handleInvalidDataException(HttpServletRequest req, InvalidDataException exception) {
		LOG.error("FORBIDDEN", exception);
		return createErrorResponse(req, exception.getMessage(), exception.getErrorCode());
	}

	/**
	 * Handler for MethodArgumentTypeMismatchException.
	 *
	 * @param req
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseBody
	ErrorInfo handleInvalidDataException(HttpServletRequest req, MethodArgumentTypeMismatchException exception) {
		LOG.error("BAD_REQUEST", exception);
		return createErrorResponse(req, "BAD_REQUEST", IMSErrorCodeConstant.BAD_REQUEST);
	}

	/**
	 * Handler for InsufficientStockException.
	 *
	 * @param req
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(InsufficientStockException.class)
	@ResponseBody
	ErrorInfo handleInsufficientStockException(HttpServletRequest req, InsufficientStockException exception) {
		LOG.error("Insufficient Stock", exception);
		return createErrorResponse(req, exception.getMessage(), exception.getErrorCode());
	}

	/**
	 * 
	 * Handler for BadRequestException.
	 * 
	 * @param req
	 * @param ex
	 * @param httpStatus
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadRequestException.class)
	@ResponseBody
	ErrorInfo handleBadRequestException(HttpServletRequest req, BadRequestException exception) {
		LOG.error("UNAUTHORIZED_ACCESS_ERROR exception ", exception);
		return createErrorResponse(req, exception.getMessage(), exception.getErrorCode());
	}

	
	/**
	 * 
	 * Handler for InvalidDataAccessApiUsageException.
	 * 
	 * @param req
	 * @param ex
	 * @param httpStatus
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	@ResponseBody
	ErrorInfo handleInvalidDataAccessApiUsageException(HttpServletRequest req, InvalidDataAccessApiUsageException exception) {
		LOG.error("UNAUTHORIZED_ACCESS_ERROR exception ", exception);
		return createErrorResponse(req, exception.getMessage(), IMSErrorCodeConstant.BAD_REQUEST);
	}
	
	/**
	 * @param req
	 * @param ex
	 * @param httpStatus
	 * @return
	 */
	private ErrorInfo createErrorResponse(HttpServletRequest req, String errorMessage, String errorCode) {
		ErrorInfo errorInfo = null;
		try {
			errorInfo = new ErrorInfo(errorCode, errorMessage);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return errorInfo;
	}

}
