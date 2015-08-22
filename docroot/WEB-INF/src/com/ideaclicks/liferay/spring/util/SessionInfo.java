package com.ideaclicks.liferay.spring.util;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SessionInfo implements java.io.Serializable{
	
	/**
	 * This field holds the logger for this class.
	 */
	private static final Log LOG = LogFactory.getLog(SessionInfo.class);

	public int	mnSessionStatus = -1;
	private String requestIp;
	private String sessionId;
	private String email;
	private String orgCode;
	/**
	 * @return the requestIp
	 */
	public String getRequestIp() {
		return requestIp;
	}
	/**
	 * @param requestIp the requestIp to set
	 */
	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}
	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}
	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the orgCode
	 */
	public String getOrgCode() {
		return orgCode;
	}
	/**
	 * @param orgCode the orgCode to set
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
}