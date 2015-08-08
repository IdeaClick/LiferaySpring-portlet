package com.ideaclicks.liferay.spring.controller;

import java.io.IOException;
import java.util.Map;

import javax.crypto.Cipher;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
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
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideaclicks.liferay.spring.domain.userRegistration;
import com.ideaclicks.liferay.spring.exception.MinervaException;
import com.ideaclicks.liferay.spring.service.IdeaManagementService;
import com.ideaclicks.liferay.spring.util.RandomPasswordGenerator;
import com.ideaclicks.liferay.spring.util.SendEmail;
import com.ideaclicks.liferay.spring.util.VerifyRecaptcha;
import com.liferay.portal.kernel.captcha.CaptchaMaxChallengesException;
import com.liferay.portal.kernel.captcha.CaptchaTextException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.util.PortalUtil;

@Controller("userregistrationController")
@RequestMapping("VIEW")
public class UserRegistrationController {
	
	SendEmail snd = new SendEmail();
	
	String textMessage = "Your Password is...";
	char[] pswd;
    String password;
    static Cipher cipher;
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
	
	@RenderMapping(params = "action=viewUserReg")
	public ModelAndView renderOneMethod(RenderRequest request, RenderResponse response, Model model, @ModelAttribute("user_reg") userRegistration uRegistration, BindingResult result) throws IOException,
			PortletException,MinervaException {
				return new ModelAndView("userRegistration");
	}
	
	@ActionMapping(params = "action=userReg")
	public void userReg(ActionRequest actionRequest, ActionResponse actionResponse, Model model, @ModelAttribute("user_reg") userRegistration uRegistration, BindingResult result) throws IOException,
			PortletException,MinervaException  {
		String message = null;
		try {
			
			 // get reCAPTCHA request param
	        String gRecaptchaResponse = actionRequest.getParameter("g-recaptcha-response");
	        System.out.println(gRecaptchaResponse);
	        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
	        System.out.println("Captcha Resopnse"+verify);
	    
	        if(verify) {
		
	        		pswd = RandomPasswordGenerator.generatePswd();
	        		password = pswd.toString();
		
	        		uRegistration.setPswd(password);
	        		uRegistration.setStatus("DEACTIVATE");	
			
	        		message =ideamgmtService.newUserRegistration(uRegistration);
			
	        		if(message.equalsIgnoreCase("user registration successful")){
	        			LOG.info("Registration Complete");
	        			snd.getDetails(uRegistration.getEmail(),password,uRegistration.getOrgCode());
	        			SessionMessages.add(actionRequest, "success");
	        			
	        		}
	        		else if(message.equalsIgnoreCase("user already registered")){
	        			// Hide default error message
	    				SessionErrors.add(actionRequest, "error-key");
	    				SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
	    	     		
	        			LOG.info("Already registered");
	        			SessionErrors.add(actionRequest, "error");	
	        		}
	        		else if(message.equalsIgnoreCase("your organization not registered")){
	        			// Hide default error message
	    				SessionErrors.add(actionRequest, "error-key");
	    				SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
	    	     		
	        			LOG.info("organization not registered");
	        			SessionErrors.add(actionRequest, "error1");	
	        		}
	        }else{
	        	 System.out.println("Captcha Resopnse"+verify);
	     		  SessionErrors.add(actionRequest, "captcha");
	        }
			
		}  catch (MinervaException e) {
				ObjectError error = new ObjectError("User Registered",e.getMessage());
				result.addError(error);
				LOG.debug("Exception" + e.getMessage());
				
        }catch(Exception e){
            LOG.error("Exception " + e.getMessage());
        }	
	}
}
