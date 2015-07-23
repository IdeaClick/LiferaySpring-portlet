package com.ideaclicks.liferay.spring.controller;

import java.io.IOException;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.ideaclicks.liferay.spring.domain.OrganizationRegistration;
import com.ideaclicks.liferay.spring.exception.MinervaException;
import com.ideaclicks.liferay.spring.service.IdeaManagementService;

@Controller("registrationController")
@RequestMapping("VIEW")
public class organizationRegistrationController    {
	/**
     * This field holds the logger for this class.
     */
    private static final Log LOG = LogFactory.getLog(organizationRegistrationController.class);
    
	@Autowired
	private IdeaManagementService ideamgmtService;
	
	@RenderMapping
	public String orgRegistration(Map<String, Object> map){
		map.put("organizationRegistration", new OrganizationRegistration());
		return "organizationRegistration";
	}
	
	
	@ActionMapping(params = "action=addData")
	public void orgRegistration(ActionRequest actionRequest,ActionResponse actionResponse, Model model,@ModelAttribute("reg") OrganizationRegistration registration, BindingResult result) throws IOException,
			PortletException,MinervaException {
	
		try {
			ideamgmtService.organizationRegistration(registration);
		}  catch (MinervaException me) {
            // redirected to error page
            LOG.debug("check for the exception here" + me.getMessage());
        } catch (Exception e) {
            // redirected to error page
            LOG.debug("check for the exception here" + e.getMessage());
        }
		//return "org_reg";
		
	}
}
