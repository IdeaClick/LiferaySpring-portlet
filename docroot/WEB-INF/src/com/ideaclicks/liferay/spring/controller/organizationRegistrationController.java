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
import com.ideaclicks.liferay.spring.domain.OrganizationRegistration;
import com.ideaclicks.liferay.spring.exception.MinervaException;
import com.ideaclicks.liferay.spring.service.IdeaManagementService;
import com.ideaclicks.liferay.spring.util.RandomPasswordGenerator;
import com.ideaclicks.liferay.spring.util.VerifyRecaptcha;
import com.ideaclicks.liferay.spring.util.SendEmail;
import com.liferay.portal.kernel.captcha.CaptchaMaxChallengesException;
import com.liferay.portal.kernel.captcha.CaptchaTextException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;

@Controller("registrationController")
@RequestMapping("VIEW")
public class organizationRegistrationController    {
	
/*	sendEmail class for the send email after successfully organization registration*/
	
	SendEmail snd = new SendEmail();
	
	String textMessage = "Your Password is...";
	
    char[] pswd;
    String password;
    static Cipher cipher;
	
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
	
	
	@RenderMapping(params = "action=viewOrgReg")
	public ModelAndView renderOneMethod(RenderRequest request, RenderResponse response, Model model, @ModelAttribute("reg") OrganizationRegistration registration, BindingResult result) throws IOException,
			PortletException,MinervaException {
				return new ModelAndView("organizationRegistration");
	}
	
	@ActionMapping(params = "action=orgReg")
	public void organizationRegistration(ActionRequest actionRequest, ActionResponse actionResponse, Model model, @ModelAttribute("reg") OrganizationRegistration registration, BindingResult result) throws IOException,
			PortletException,MinervaException {
		boolean res = false;
		try {
			
			 // get reCAPTCHA request param
	        String gRecaptchaResponse = actionRequest.getParameter("g-recaptcha-response");
	        System.out.println(gRecaptchaResponse);
	        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
	        System.out.println("Captcha Resopnse"+verify);
	     if(verify)
	        {
				pswd = RandomPasswordGenerator.generatePswd();
		        password = pswd.toString();
		   		System.out.println("passwor"+pswd);
		   		
				registration.setPswd(password);
				registration.setStatus("DEACTIVATE");
		
				System.out.println("sss");
				res = ideamgmtService.organizationRegistration(registration);
				System.out.println("valueeeeeeeeee"+res);
				if(res){
							LOG.info("Registration Complete");
							//snd.getDetails(registration.getEmail(),password);
							SessionMessages.add(actionRequest, "success");
						}
						else{
							LOG.info("Organization already registered");
							SessionErrors.add(actionRequest, "error");
							}
	        }
	     	else
	     	{
	     		  System.out.println("Captcha Resopnse"+verify);
	     		  SessionErrors.add(actionRequest, "captcha");
	     	}
			
				}  catch (MinervaException e) {
					ObjectError error = new ObjectError("Organization Registered",e.getMessage());
					result.addError(error);
					LOG.debug("Exception" + e.getMessage());
					
	        }catch(Exception e){
	            LOG.error("Exception " + e.getMessage());

	        }		
	}
	
}
