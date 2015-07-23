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
import com.ideaclicks.liferay.spring.domain.userRegistration;
import com.ideaclicks.liferay.spring.exception.AdminException;
import com.ideaclicks.liferay.spring.exception.MinervaException;
import com.ideaclicks.liferay.spring.service.IdeaManagementService;


@Controller("userregistrationController")
@RequestMapping("VIEW")
public class UserRegistrationController {
	/**
     * This field holds the logger for this class.
     */
    private static final Log LOG = LogFactory.getLog(UserRegistrationController.class);
    
	@Autowired
	private IdeaManagementService ideamgmtService;
	
	@RenderMapping
	public String userRegistrationn(Map<String, Object> map) {
		map.put("userRegistration", new userRegistration());
		return "userRegistration";
	}
	
	
	@ActionMapping(params = "action=adduser")
	public void addContact(ActionRequest actionRequest,ActionResponse actionResponse, Model model,@ModelAttribute("user_reg") userRegistration uRegistration, BindingResult result) throws IOException,
			PortletException,MinervaException  {
		//System.out.println("result"+ParamUtil.getString(actionRequest,"firstname"));
		
		System.out.println("User Name"+uRegistration.getUserName());
		
		try {
			ideamgmtService.newUserRegistration(uRegistration);
		}  catch (MinervaException me) {
            // redirected to error page
            LOG.debug("check for the exception here" + me.getMessage());
        } catch (Exception e) {
            // redirected to error page
            LOG.debug("check for the exception here" + e.getMessage());
        }
		//return "user_reg";
		
	}

}
