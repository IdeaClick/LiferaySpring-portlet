package com.ideaclicks.liferay.spring.controller;
import java.io.IOException;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.ideaclicks.liferay.spring.domain.OrganizationRegistration;
import com.ideaclicks.liferay.spring.exception.MinervaException;
import com.ideaclicks.liferay.spring.service.IdeaManagementService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.ideaclicks.liferay.spring.util.LiferaySessionUtil;
import com.ideaclicks.liferay.spring.util.VerifyRecaptcha;

@Controller("loginController")
@RequestMapping("VIEW")
public class LoginController extends MVCPortlet  {
	
	/**
     * This field holds the logger for this class.
     */
    private static final Log LOG = LogFactory.getLog(LoginController.class);

	@Autowired
	private IdeaManagementService ideamgmtService;
	
	@RenderMapping 
	public String login(RenderRequest request, RenderResponse response, Model model,Map<String, Object> map) throws IOException,
	PortletException {		
		return "login";
	}
	
	@RenderMapping(params = "action=loginForm")
	public ModelAndView renderOneMethod(RenderRequest request, RenderResponse response, Model model, @ModelAttribute("login") OrganizationRegistration registration, BindingResult result) throws IOException,
			PortletException,MinervaException {
				return new ModelAndView("login");
	}
	
	@RenderMapping(params = "action=login") 
	public ModelAndView Authentication(RenderRequest renderRequest, RenderResponse renderResponse, Model model, @ModelAttribute("login") OrganizationRegistration reg, BindingResult result) throws IOException,
	PortletException,SecurityException{ 
		boolean value = true;
		try {

			// get reCAPTCHA request param
	        String gRecaptchaResponse = renderRequest.getParameter("g-recaptcha-response");
	        System.out.println(gRecaptchaResponse);
	        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
	        System.out.println("Captcha Resopnse"+verify);
	     if(verify){
	    	 
			String emailId = ParamUtil.getString(renderRequest,"email");
			String password = ParamUtil.getString(renderRequest,"pswd");
			String orgcode =  ParamUtil.getString(renderRequest,"orgCode");
			System.out.println("Email Id:"+emailId);
			
			value = ideamgmtService.authenticateUser(emailId, password ,orgcode);
			
			if(value){
				System.out.println("Pass:"+password);
				LiferaySessionUtil.setGlobalSessionAttribute("sessionValue",orgcode, renderRequest);
				PortletSession session = renderRequest.getPortletSession();
				session.setAttribute("loginUserEmail",emailId, PortletSession.PORTLET_SCOPE);
				SessionMessages.add(renderRequest, "loginsuccess");
				return new ModelAndView("submitIdea","categoryList",ideamgmtService.getIdeasCategoryList());
			}
			else{
					// Hide default error message
					SessionErrors.add(renderRequest, "error-key");
					SessionMessages.add(renderRequest, PortalUtil.getPortletId(renderRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
					//display error message
					SessionErrors.add(renderRequest, "error");
					return new ModelAndView("login");
				}
	     }else{
	    	 System.out.println("Captcha Resopnse"+verify);
    		  SessionErrors.add(renderRequest, "captcha");
	     }
			
			}catch(SecurityException se) {
				LOG.error("SecurityException " + se.getMessage());
				ObjectError error = new ObjectError("InvalidUser","Invalid User name / Password ");
				result.addError(error);
			} catch (Exception e) {
				ObjectError error = new ObjectError("InvalidUser","Invalid User name / Password ");
				result.addError(error);
			}
		
		return new ModelAndView("login");
		}
}	