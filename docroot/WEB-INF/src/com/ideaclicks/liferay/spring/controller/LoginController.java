package com.ideaclicks.liferay.spring.controller;
import com.liferay.portal.util.PortalUtil;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import javax.portlet.PortletRequest;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideaclicks.liferay.spring.exception.AdminException;
import com.ideaclicks.liferay.spring.exception.MinervaException;
import com.ideaclicks.liferay.spring.domain.OrganizationRegistration;
import com.ideaclicks.liferay.spring.service.IdeaManagementService;
import com.ideaclicks.liferay.spring.service.IdeaManagementServiceImpl;

@Controller("loginController")
@RequestMapping("VIEW")
public class LoginController    {
	
	/**
     * This field holds the logger for this class.
     */
    private static final Log LOG = LogFactory.getLog(LoginController.class);

	@Autowired
	private IdeaManagementService ideamgmtService;
	
	@RenderMapping
	public String login(Map<String, Object> map) throws MinervaException{
		map.put("login", new OrganizationRegistration());
		try {
			map.put("OrganizationList",ideamgmtService.getOrganizationList());
		} catch (MinervaException me) {
            // redirected to error page
            LOG.debug("check for the exception here" + me.getMessage());
		}
		return "login";
	}
	
	/*@RenderMapping(params = "action=add") 
	public ModelAndView renderOneMethod(RenderRequest request, RenderResponse response, Model model, @ModelAttribute("login") OrganizationRegistration reg, BindingResult result) throws IOException,
	PortletException,SecurityException ,MinervaException{ 
		boolean b = false;
		LOG.debug("Email"+reg.getEmail());
		//LOG.debug("password"+reg.getPswd());
		 
		
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request)); 
		String myArticleId = httpReq.getParameter("articleId");
		System.out.println("Id:"+myArticleId);
		try {
				b=ideamgmtService.authenticateUser(reg.getEmail(), reg.getPswd());
				
			}catch(SecurityException se) {
            LOG.error("SecurityException " + se.getMessage());
            ObjectError error = new ObjectError("InvalidUser","Invalid User name / Password ");
            result.addError(error);
        } catch (MinervaException e) {
            ObjectError error = new ObjectError("InvalidUser","Invalid User name / Password ");
            result.addError(error);
        }
		if(b){
				//return new ModelAndView("view_profile", "viewProfileCmd", ideamgmtService.viewProfile());
			}
		else{
				return new ModelAndView("login");
			}
		}*/
	}  
	
