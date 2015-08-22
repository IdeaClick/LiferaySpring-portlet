package com.ideaclicks.liferay.spring.util;

public class ServiceStatus {
	
	private String status;
	
	private String errorKey;
	
	private String errorMsg;
	
	private String successMsg;

	/**
	 * @return the successMsg
	 */
	public String getSuccessMsg() {
		return successMsg;
	}

	/**
	 * @param successMsg the successMsg to set
	 */
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the errorKey
	 */
	public String getErrorKey() {
		return errorKey;
	}

	/**
	 * @param errorKey the errorKey to set
	 */
	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	
}
