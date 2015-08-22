package com.ideaclicks.liferay.spring.exception;

/**
 * This class extends the Exception class and represents any exceptional
 * condition for the application.
 * 
 * @author  Amol Shirude.
 * 
 */
public class MinervaException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * errorCode keep track of type of error.
	 */
	private String errorCode;

	/**
	 * This is constructor for initialising fields.
	 */
	public MinervaException() {
		super();
	}

	public MinervaException(String msg, String errCode) {
		super(msg);
		this.errorCode = errCode;
	}

	/**
	 * This constructor overloaded with message.
	 * 
	 * @param msg
	 */
	public MinervaException(String msg) {
		super(msg);
	}

	/**
	 * This method converts the object to String.
	 * 
	 * @see java.lang.Throwable#toString()
	 */
	public String toString() {
		return "MinervaException Occured :: " + getErrorCode() + "::"
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
