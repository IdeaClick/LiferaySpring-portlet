package com.ideaclicks.liferay.spring.exception;

/**
 * This class extends the Exception class and represents any exceptional
 * conditions in the Security Service.
 * 
 * @author  Amol Shirude.
 * 
 */
public class SecurityException extends MinervaException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * errorCode keep track of type of error
	 */
	private String errorCode;

	/**
	 * This is constructor for initialising fields.
	 */
	public SecurityException() {
		super();
	}

	/**
	 * This constructor overloaded with message.
	 * 
	 * @param msg
	 */
	public SecurityException(String msg) {
		super(msg);
	}

	/**
	 * This method converts the object to String.
	 * 
	 * @see java.lang.Throwable#toString()
	 */
	public String toString() {
		return "SecurityException Occured :: " + getErrorCode() + "::"
				+ getMessage();
	}

	/**
	 * This method get the error code.
	 * 
	 * @return error code
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * This method set the error code.
	 * 
	 * @param errorCode
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
