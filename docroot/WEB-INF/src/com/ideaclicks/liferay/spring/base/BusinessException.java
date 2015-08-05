package com.ideaclicks.liferay.spring.base;

/**
 * Exception thrown by the business tier objects.
 * 
 * @author asarin
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1934035678938111357L;

	public BusinessException(String message) {
		super(message);
	}
}
