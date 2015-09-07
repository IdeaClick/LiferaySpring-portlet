package com.ideaclicks.liferay.spring.controller;

import java.io.IOException;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.validation.Valid;

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
import com.ideaclicks.liferay.spring.domain.UserRegistration;
import com.ideaclicks.liferay.spring.exception.MinervaException;
import com.ideaclicks.liferay.spring.service.IdeaManagementService;
import com.ideaclicks.liferay.spring.util.IClicksEncriptionDecription;
import com.ideaclicks.liferay.spring.util.SendEmail;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;

@Controller("forgetpasswordController")
@RequestMapping("VIEW")
public class ForgetPasswordController{

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
	
	@RenderMapping(params = "action=forgetPswd")
	public ModelAndView renderOneMethod(RenderRequest request, RenderResponse response, Model model, @ModelAttribute("forgetpassword") OrganizationRegistration registration, BindingResult result) throws IOException,
	PortletException,MinervaException {
		return new ModelAndView("forgetPassword");
	}

	@RenderMapping(params = "action=forgetPasswordAction")
	public ModelAndView handlePostRequest(RenderRequest renderRequest, RenderResponse renderResponse, Model model,@ModelAttribute("forgetpassword") OrganizationRegistration registration,
			BindingResult result)throws IOException,PortletException {
	
		
		try {
			String password = "";
			String email = ParamUtil.getString(renderRequest,"email");
			LOG.info("Email ID:"+email);
			
			password=IClicksEncriptionDecription.decryptPassword(ideamgmtService.forgetPassword(email));
			if(!(password == null)){
				LOG.info("Password is:"+password);
				snd.sendEmailForgetPassword(email,password);
				PortletSession session = renderRequest.getPortletSession();
				session.setAttribute("email",email, PortletSession.APPLICATION_SCOPE);
				return new ModelAndView("forget_password_success");
			}
			else{
				// Hide default error message
				SessionErrors.add(renderRequest, "error-key");
				SessionMessages.add(renderRequest, PortalUtil.getPortletId(renderRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
				//display error message
				SessionErrors.add(renderRequest, "error");
			}
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Forget Password Exception"+e.getStackTrace());
			LOG.debug("check for the exception here" + e.getMessage());
		}
		return new ModelAndView("forgetPassword");
	}
}


