package com.ideaclicks.liferay.spring.base;

/**
 * ActionMessage class acts as a transfer object between the browser and the web-tier
 * of the application. The AJAX request handler methods should return ActionMessage
 * object as the return type. DWR uses a bean converter to allow access to ActionMessage
 * object as a Javascript associative array.
 * 
 * @author asarin
 */
public class ActionMessage {
	//--return code
	private int code;
	
	//--return message
	private String message;
	
	//-- data (if any) that needs to be sent to the browser for rendering
	private String data;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
