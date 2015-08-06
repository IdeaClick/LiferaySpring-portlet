package com.ideaclicks.liferay.spring.controller;

import java.io.IOException;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.ideaclicks.liferay.spring.domain.OrganizationRegistration;
import com.ideaclicks.liferay.spring.exception.MinervaException;
import com.ideaclicks.liferay.spring.service.IdeaManagementService;
import com.ideaclicks.liferay.spring.util.SendEmail;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Base64;

@Controller("forgetpasswordController")
@RequestMapping("VIEW")
public class ForgetPasswordController{
	
	static Cipher cipher;
	SendEmail snd = new SendEmail();
	/**
     * This field holds the logger for this class.
     */
    private static final Log LOG = LogFactory.getLog(ForgetPasswordController.class);
    
	@Autowired
	private IdeaManagementService ideamgmtService;
	
	@RenderMapping
	public String forgetPassword(Map<String, Object> map) {
		map.put("forgetPassword", new OrganizationRegistration());
		return "forgetPassword";
	}
	
	
	
	@RenderMapping(params = "action=forgetPassword")
	public ModelAndView forgetpass(RenderRequest renderRequest,RenderResponse renderResponse, Model model,@ModelAttribute("forgetpassword") OrganizationRegistration registration, BindingResult result) throws IOException,
			PortletException,MinervaException {
		
		String password=null;
		System.out.println("Email Id:"+registration.getEmail());
		try {
				password=ideamgmtService.forgetPassword(registration.getEmail());
					if(!(password == null)){
							System.out.println("Password is:"+password);
							//snd.getDetails(registration.getEmail(),decryptedText);
							return new ModelAndView("login");
					}
					else{
							SessionErrors.add(renderRequest, "error");
						}
		}catch (MinervaException me) {
            // redirected to error page
            LOG.debug("check for the exception here" + me.getMessage());
        } catch (Exception e) {
            // redirected to error page
            LOG.debug("check for the exception here" + e.getMessage());
        }
		return new ModelAndView("forgetPassword");
	}
	
}
	

