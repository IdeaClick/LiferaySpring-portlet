package com.ideaclicks.liferay.spring.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Utility class for use by AJAX request handlers.
 * 
 * @author asarin
 */
public class DwrUtility {
	/**
	 * Returns the messageSource bean configured in the applicationContext.xml file.
	 * 
	 * @param request HttpServletRequest
	 * @return messageSouce bean
	 */
	public static MessageSource getMessageSource(HttpServletRequest request) {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getSession(false).getServletContext());
		return (ResourceBundleMessageSource) context.getBean(ApplicationConstants.MESSAGE_SOURCE_BEAN_NAME);
	}
}