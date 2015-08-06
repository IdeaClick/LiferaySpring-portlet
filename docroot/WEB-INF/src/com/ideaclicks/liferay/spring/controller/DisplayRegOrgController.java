package com.ideaclicks.liferay.spring.controller;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.ideaclicks.liferay.spring.exception.MinervaException;
import com.ideaclicks.liferay.spring.service.IdeaManagementService;

@Controller("displayController")
@RequestMapping("VIEW")
public class DisplayRegOrgController {
	/**
     * This field holds the logger for this class.
     */
    private static final Log LOG = LogFactory.getLog(DisplayRegOrgController.class);

	@Autowired
	private IdeaManagementService ideamgmtService;
	@RenderMapping
	public String home(Map<String, Object> map)throws MinervaException{
		try {
			map.put("OrganizationList",ideamgmtService.getOrganizationNameList());
		} catch (MinervaException me) {
            // redirected to error page
            LOG.debug("check for the exception here" + me.getMessage());
		}
		return "registeredOrganization";
	}

}
