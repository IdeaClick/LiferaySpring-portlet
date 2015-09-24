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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideaclicks.liferay.spring.domain.UserRegistration;
import com.ideaclicks.liferay.spring.exception.MinervaException;
import com.ideaclicks.liferay.spring.service.IdeaManagementService;
import com.ideaclicks.liferay.spring.util.GlobalConstants;
import com.ideaclicks.liferay.spring.util.IClicksEncriptionDecription;
import com.ideaclicks.liferay.spring.util.RandomPasswordGenerator;
import com.ideaclicks.liferay.spring.util.SendEmail;
import com.ideaclicks.liferay.spring.util.VerifyRecaptcha;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.util.PortalUtil;

@Controller("userregistrationController")
@RequestMapping("VIEW")
public class UserRegistrationController {

	/**
	 * This field holds the logger for this class.
	 */
	private static final Log LOG = LogFactory.getLog(UserRegistrationController.class);

	SendEmail snd = new SendEmail();

	@Autowired
	private IdeaManagementService ideamgmtService;

	@Autowired
	@Qualifier("userRegistrationValidator")
	private Validator validator;

	/*@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}*/

	@Resource(name = "validator")
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	@RenderMapping
	public String UserRegistrationn(Map<String, Object> map) {
		map.put("userRegistration", new UserRegistration());
		return "userRegistration";
	}

	@RenderMapping(params = "action=viewUserReg")
	public ModelAndView renderOneMethod(RenderRequest request, RenderResponse response, Model model, @ModelAttribute("user_reg") UserRegistration uRegistration, BindingResult result) throws IOException,
	PortletException,MinervaException {
		return new ModelAndView("userRegistration");
	}

	@RenderMapping(params = "action=userReg")
	public ModelAndView handlePostRequest(RenderRequest renderRequest, RenderResponse renderResponse, Model model,@Valid @ModelAttribute("user_reg") UserRegistration uRegistration,
			BindingResult result,Map<String, Object> map)throws IOException,PortletException {
		String message="";
		try{
			// get reCAPTCHA request param
			String gRecaptchaResponse = renderRequest.getParameter("g-recaptcha-response");
			boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
			LOG.info("Captcha Resopnse"+verify);
			if(verify) {
				LOG.info("Before validate new user registration::");
				validator.validate(uRegistration, result);
				if (result.hasErrors()) {
					LOG.info("validation  failed");
				}
				else{
					LOG.info("Success Validation ======>>>");
					String pswd;
					//pswd =RandomPasswordGenerator.generatePswd().toString();

					uRegistration.setPswd(IClicksEncriptionDecription.encryptPassword(uRegistration.getPswd()));
					uRegistration.setStatus("DEACTIVATE");	

					message =ideamgmtService.newUserRegistration(uRegistration);

					if(message.equalsIgnoreCase("user registration successful")){
						LOG.info("Registration Complete");
						String url = GlobalConstants.LOGIN_URL + GlobalConstants.QUESTIONMARK +GlobalConstants.ORGCODE+ GlobalConstants.EQUAL + uRegistration.getOrgCode();
						snd.sendEmailUser(uRegistration.getEmail(),uRegistration.getPswd(),uRegistration.getOrgCode(),url);
						SessionMessages.add(renderRequest, "success");
						PortletSession session = renderRequest.getPortletSession();
						session.setAttribute("email",uRegistration.getEmail(), PortletSession.APPLICATION_SCOPE);
						return new ModelAndView("success");

					}
					else if(message.equalsIgnoreCase("user already registered")){
						// Hide default error message
						SessionErrors.add(renderRequest, "error-key");
						SessionMessages.add(renderRequest, PortalUtil.getPortletId(renderRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);

						LOG.info("Already registered");
						SessionErrors.add(renderRequest, "error");
						return new ModelAndView("userRegistration");
					}
					else if(message.equalsIgnoreCase("your organization not registered")){
						// Hide default error message
						SessionErrors.add(renderRequest, "error-key");
						SessionMessages.add(renderRequest, PortalUtil.getPortletId(renderRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);

						LOG.info("organization not registered");
						SessionErrors.add(renderRequest, "error1");
						return new ModelAndView("userRegistration");
					}
				}
			}else{
				// Hide default error message
				SessionErrors.add(renderRequest, "error-key");
				SessionMessages.add(renderRequest, PortalUtil.getPortletId(renderRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
				LOG.debug("Captcha Resopnse"+verify);
				SessionErrors.add(renderRequest, "captcha");
				return new ModelAndView("userRegistration");
			}
		}catch (MinervaException e) {
			ObjectError error = new ObjectError("User Registered",e.getMessage());
			result.addError(error);
			LOG.error("Exception" + e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception " + e.getMessage());
		}	
		return new ModelAndView("userRegistration");
	}          

}