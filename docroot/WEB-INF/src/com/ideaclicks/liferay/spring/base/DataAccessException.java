package com.ideaclicks.liferay.spring.base;

/**
 * Exception thrown by Data Access tier of the application.
 * 
 * @author asarin
 */
public class DataAccessException extends RuntimeException {
	private static final long serialVersionUID = 561744244707133387L;

	public DataAccessException(String message) {
		super(message);
	}
}
