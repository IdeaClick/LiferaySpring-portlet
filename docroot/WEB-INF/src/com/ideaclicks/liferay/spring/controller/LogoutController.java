package com.ideaclicks.liferay.spring.controller;

import java.io.IOException;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.ideaclicks.liferay.spring.util.LiferaySessionUtil;

@Controller("logoutController")
@RequestMapping("VIEW")
public class LogoutController {
	
	/**
     * This field holds the logger for this class.
     */
    private static final Log LOG = LogFactory.getLog(LogoutController.class);
	
	@RenderMapping 
	public String logout(RenderRequest renderRequest, RenderResponse renderResponse, Model model,Map<String, Object> map) throws IOException,
	PortletException {
		
		try{
			
			Object sessionvalue=  LiferaySessionUtil.getGlobalSessionAttribute("sessionValue", renderRequest);
			String currentSessionvalue = sessionvalue.toString();
			System.out.println("Current Session Value"+currentSessionvalue);
			LiferaySessionUtil.setGlobalSessionAttribute("sessionValue","", renderRequest);
			
			
		}catch (Exception e) {
			
			LOG.debug("check for the exception here" + e.getMessage());
		}
			
		return "logout";
	}

}
