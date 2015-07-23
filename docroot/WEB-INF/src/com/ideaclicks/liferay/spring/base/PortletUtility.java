package com.ideaclicks.liferay.spring.base;

import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;

/**
 * PortletUtility class provides static access to methods defined in PortalService interfaces.
 * 
 * @author asarin
 */
public class PortletUtility {
	private static PortalServices portalServices;
	
	public static void setPortalServices(PortalServices portal) {
		portalServices = portal;
	}
	
	public static String getPortletInstanceId(RenderRequest request) {
		return portalServices.getPortletInstanceId(request);
	}
	
	public static String getSessionFormName(String instanceId, String commandName) {
		return portalServices.getSessionFormName(instanceId, commandName);
	}
	
	public static String getUsername(PortletRequest request) {
		return portalServices.getUsername(request);
	}
}