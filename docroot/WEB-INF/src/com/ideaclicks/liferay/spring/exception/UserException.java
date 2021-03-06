package com.ideaclicks.liferay.spring.exception;

/**
 * This class extends the Exception class. This class is used to indicate any
 * failure of business validations in the UserService.
 * 
 * @author  Amol Shirude.
 * 
 */
public class UserException extends MinervaException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * errorCode keep track of type of error
	 */
	private String errorCode;

	/**
	 * This is constructor for initialising fields.
	 */
	public UserException() {
		super();
	}

	/**
	 * This constructor overloaded with message.
	 * 
	 * @param msg
	 */
	public UserException(String msg) {
		super(msg);
	}

	/**
	 * This constructor overloaded with message and error code.
	 * 
	 * @param msg
	 */
	public UserException(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}

	/**
	 * This method converts the object to String.
	 * 
	 * @see java.lang.Throwable#toString()
	 */
	public String toString() {
		return "User Exception Occured :: " + getErrorCode() + "::"
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
