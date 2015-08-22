package com.ideaclicks.liferay.spring.exception;

/**
 * This class extends the Exception class. This class is used to indicate any
 * failure of business validations in the AdminService.
 * 
 * @author Amol Shirude.
 * 
 */
public class AdminException extends MinervaException {

	private static final long serialVersionUID = 1L;

	/**
	 * errorCode keep track of type of error
	 */
	private String errorCode;

	/**
	 * This is constructor for initialising fields.
	 */
	public AdminException() {
		super();
	}

	/**
	 * This constructor overloaded with message.
	 * 
	 * @param msg
	 */
	public AdminException(String msg) {
		super(msg);
	}

	/**
	 * This constructor overloaded with message and error code.
	 * 
	 * @param msg
	 */
	public AdminException(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}

	/**
	 * This method converts the object to String.
	 * 
	 * @see java.lang.Throwable#toString()
	 */
	public String toString() {
		return "Admin Exception Occured :: " + getErrorCode() + "::"
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
