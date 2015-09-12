package com.ideaclicks.liferay.spring.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.ideaclicks.liferay.spring.domain.Contact;
import com.ideaclicks.liferay.spring.service.IdeaManagementService;
import com.ideaclicks.liferay.spring.util.SendEmail;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;

@Controller("contactUsController")
@RequestMapping("VIEW")
public class ContactUsController {

	/**
	 * This field holds the logger for this class.
	 */
	private static final Log LOG = LogFactory.getLog(ContactUsController.class);
	
	SendEmail snd = new SendEmail();

	private Validator validator;

	@Resource(name = "validator")
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	@Autowired
	private IdeaManagementService ideamgmtService;


	@RenderMapping
	public ModelAndView renderOneMethod(RenderRequest request, RenderResponse response,Model model, @ModelAttribute("contactusform") Contact contact,
			BindingResult result) throws IOException,PortletException{
		return new ModelAndView("contact_us");
	}

	@RenderMapping(params = "action=ContactUs")
	public ModelAndView handlePostRequest(RenderRequest actionRequest, RenderResponse actionResponse, Model model,@Valid @ModelAttribute("contactusform") Contact contact,
			BindingResult result)throws IOException,PortletException {

		LOG.info("Email ID:"+ParamUtil.getString(actionRequest,"email"));
		System.out.println("Name="+contact.getYourName());

		try {
			validator.validate(contact, result);
			if (result.hasErrors()) {
				LOG.info("validation  failed");
			}
			else{
				LOG.info("Success Validation ======>>>");
				boolean value = ideamgmtService.contactUs(contact);
				if(value){
					System.out.println("result"+value);
					SessionMessages.add(actionRequest, "success");
					snd.sendEmailContactUs(contact.getYourName(),contact.getEmail(),contact.getMessage());
				}
				else{
					// Hide default error message
					SessionErrors.add(actionRequest, "error-key");
					SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
					//display error message
					SessionErrors.add(actionRequest, "error");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception"+e.getStackTrace());
			LOG.debug("check for the exception here" + e.getMessage());
		}
		return new ModelAndView("contact_us");
	}

}
