package com.ideaclicks.liferay.spring.base;

import javax.portlet.PortletRequest;

/**
 * PortalService interface declares all the methods that are dependent in some way
 * on the portal server on which the application is to be deployed.
 * 
 * @author asarin
 */
public interface PortalServices {
	/**
	 * Returns the instance id of the portlet. This portlet Id information
	 * is needed by DWR objects to access the Spring beans that are stored in
	 * session. The logic to obtain instance ids varies from portal servers to portal
	 * servers.
	 * 
	 * @param request HttpServletRequest
	 * @return String representation of instance Id
	 */
	public String getPortletInstanceId(PortletRequest request);
	
	/**
	 * Returns the name with which the Spring command object is stored in the session.
	 * The name of the command object stored in the session is a combination of 
	 * portlet instance id and the command name (defined in the application context XML )
	 * 
	 * @param instanceId portlet's instance id
	 * @param commandName name of the command object defined in the 
	 * 										application context XML
	 * @return
	 */
	public String getSessionFormName(String instanceId, String commandName);
	
	/**
	 * Returns the username from the userId
	 * 
	 * @param userId
	 * @return
	 */
	public String getUsername(PortletRequest request);
	
	/**
	 * Returns the full name of the user.
	 * 
	 * @param request
	 * @return
	 */
	public String getUserFullName(PortletRequest request);
}
