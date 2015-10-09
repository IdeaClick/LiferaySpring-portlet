package com.ideaclicks.liferay.spring.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hsqldb.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.ideaclicks.liferay.spring.util.IClicksEncriptionDecription;
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

	@Autowired
	@Qualifier("orgRegistrationValidator")
	private Validator validator;

	@Resource(name = "validator")
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	SendEmail snd = new SendEmail();

	/**
	 * This field holds the logger for this class.
	 */
	private static final Log LOG = LogFactory.getLog(OrganizationRegistrationController.class);

	@Autowired
	private IdeaManagementService ideamgmtService;

	@RenderMapping
	public String orgRegistration(RenderRequest renderRequest, RenderResponse renderResponse,Map<String, Object> map){
		renderResponse.setTitle("Organization Registration");
		map.put("organizationRegistration", new OrganizationRegistration());
		return "organizationRegistration";
	}


	@RenderMapping(params = "action=viewOrgReg")
	public ModelAndView renderOneMethod(RenderRequest renderRequest, RenderResponse renderResponse, Model model, @ModelAttribute("reg") OrganizationRegistration registration, BindingResult result) throws IOException,
	PortletException,MinervaException {
		renderResponse.setTitle("Organization Registration");
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
					String pswd= registration.getPswd();
			
					registration.setPswd(IClicksEncriptionDecription.encryptPassword(pswd));
					registration.setStatus("DEACTIVATE");
					registration.setUsertype("Admin");

					LOG.info("Organization Name:"+registration.getOrgName()+"Organization Code:"+registration.getOrgCode()+"Organization Type:"+registration.getOrgType()+
							"Organization Email:"+registration.getEmail()+"Organization Contact:"+registration.getContact());

					servicestatus = ideamgmtService.organizationRegistration(registration);
					if(servicestatus.getStatus() == GlobalConstants.SUCCESS){
						LOG.info("Registration Complete");

						//generate login url with adding some attribute
						String url = GlobalConstants.LOGIN_URL + GlobalConstants.QUESTIONMARK +GlobalConstants.ORGCODE+ GlobalConstants.EQUAL + registration.getOrgCode();
						System.out.println(" check session b4 send mail " + Session.INFO_ID);
						snd.sendEmailOrganization(registration.getOrgName(),registration.getOrgCode(),registration.getEmail(),pswd,url);
						PortletSession session = renderRequest.getPortletSession();
						session.setAttribute("email",registration.getEmail(), PortletSession.APPLICATION_SCOPE);
				
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
		}catch (MinervaException ee) {
			ObjectError error = new ObjectError("Organization Registered",ee.getMessage());
			result.addError(error);
			LOG.debug("Exception" + ee.getMessage());
			
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception " + e.getMessage());
			LOG.error("Error===>>>>"+ e.getStackTrace().toString());
		}
		return new ModelAndView("organizationRegistration");
	}	
}
