package com.ideaclicks.liferay.spring.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hsqldb.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideaclicks.liferay.spring.domain.OrganizationRegistration;
import com.ideaclicks.liferay.spring.exception.MinervaException;
import com.ideaclicks.liferay.spring.service.IdeaManagementService;
import com.ideaclicks.liferay.spring.util.GlobalConstants;
import com.ideaclicks.liferay.spring.util.RandomPasswordGenerator;
import com.ideaclicks.liferay.spring.util.SendEmail;
import com.ideaclicks.liferay.spring.util.ServiceStatus;
import com.ideaclicks.liferay.spring.util.VerifyRecaptcha;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.util.PortalUtil;

@Controller("registrationController")
@RequestMapping("VIEW")
public class OrganizationRegistrationController    {
	
	ServiceStatus servicestatus = null;
	
	private Validator validator;
	
	@Resource(name = "validator")
    public void setValidator(Validator validator) {
        this.validator = validator;
    }
	
  //sendEmail class for the send email after successfully organization registration
	
	SendEmail snd = new SendEmail();
	
    char[] pswd;
    String password;
    static Cipher cipher;
    
    /**
     * This field holds the logger for this class.
     */
    
    private static final Log LOG = LogFactory.getLog(OrganizationRegistrationController.class);
    
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
	
	@RenderMapping(params = "action=orgRegistration")
	public ModelAndView handlePostRequest(RenderRequest renderRequest, RenderResponse renderResponse, Model model,@Valid @ModelAttribute("reg") OrganizationRegistration registration,
            BindingResult result)throws IOException,PortletException {
		try {
			// get reCAPTCHA request param
	        String gRecaptchaResponse = renderRequest.getParameter("g-recaptcha-response");
	        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
	        System.out.println("Captcha Resopnse"+verify);
	        if(verify){
	        	LOG.info("Before validate new Organization registration::");
	            validator.validate(registration, result);
	            if (result.hasErrors()) {
	                LOG.info("validation  failed");
	            }
	            else{
	                LOG.info("Success Validation ======>>>");
	                pswd = RandomPasswordGenerator.generatePswd();
	                password = pswd.toString();
	                registration.setPswd(password);
	                registration.setStatus("DEACTIVATE");
					
	                LOG.info("Organization Name:"+registration.getOrgName()+"Organization Code:"+registration.getOrgCode()+"Organization Type:"+registration.getOrgType()+
						"Organization Email:"+registration.getEmail()+"Organization Contact:"+registration.getContactNo());
	             
	                servicestatus = ideamgmtService.organizationRegistration(registration);
	                 if(servicestatus.getStatus() == GlobalConstants.SUCCESS){
						LOG.info("Registration Complete");
							
						//generate login url with adding some attribute
						String url = GlobalConstants.LOGIN_URL + GlobalConstants.QUESTIONMARK +GlobalConstants.ORGCODE+ GlobalConstants.EQUAL + registration.getOrgCode();
						System.out.println(" check session b4 send mail " + Session.INFO_ID);
						snd.sendEmail(registration.getEmail(),password,registration.getOrgCode(),url);
						//model.addAttribute("reg", new OrganizationRegistration());
						System.out.println(" after send emil");
						
						System.out.println(" check session after mail sent" + Session.INFO_ID);
						
						System.out.println(" added render request");
						return new ModelAndView("success");
						
	                 }
					else if(servicestatus.getStatus() == GlobalConstants.FAILED){
							// Hide default error message
			     			SessionErrors.add(renderRequest, "error-key");
			     			SessionMessages.add(renderRequest, PortalUtil.getPortletId(renderRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
							LOG.info(servicestatus.getErrorMsg());
							SessionErrors.add(renderRequest, servicestatus.getErrorKey());
							return new ModelAndView("organizationRegistration");
					}
	            }
	        }
	     	else{
	     		// Hide default error message
	     		SessionErrors.add(renderRequest, "error-key");
	     		SessionMessages.add(renderRequest, PortalUtil.getPortletId(renderRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
	     		LOG.info("Captcha Resopnse Not Verified"+verify);
	     		SessionErrors.add(renderRequest, "captcha");
	     		return new ModelAndView("organizationRegistration");	
	     	}
		}catch (MinervaException e) {
			ObjectError error = new ObjectError("Organization Registered",e.getMessage());
			result.addError(error);
			LOG.debug("Exception" + e.getMessage());		
	    }catch(Exception e){
	        LOG.error("Exception " + e.getMessage());
	   }
		return new ModelAndView("organizationRegistration");
	}	
}
