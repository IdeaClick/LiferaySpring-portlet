package com.ideaclicks.liferay.spring.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
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
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.ideaclicks.liferay.spring.domain.Ideas;
import com.ideaclicks.liferay.spring.domain.ResetPassword;
import com.ideaclicks.liferay.spring.exception.MinervaException;
import com.ideaclicks.liferay.spring.service.IdeaManagementService;
import com.ideaclicks.liferay.spring.util.SessionInfo;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

@Controller("resetPasswordController")
@RequestMapping("VIEW")
public class ResetPasswordController extends MVCPortlet{

	/**
	 * This field holds the logger for this class.
	 */
	private static final Log LOG = LogFactory.getLog(ResetPasswordController.class);
	
	
	private Validator validator;

	@Resource(name = "validator")
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	@Autowired
	private IdeaManagementService ideamgmtService;

	@RenderMapping
	public String resetPassword(RenderRequest renderRequest, RenderResponse renderResponse, Model model,@ModelAttribute("reset_password")ResetPassword resetPassword,
			Map<String, Object> map) throws IOException,PortletException {
		try{
			PortletSession newSession = renderRequest.getPortletSession();
			SessionInfo sessInfo = (SessionInfo)newSession.getAttribute("sessionInfo",PortletSession.APPLICATION_SCOPE);
			LOG.info("Reset Password Controller Session Info"+sessInfo);
			if(sessInfo!=null){
				renderResponse.setTitle("Submit Idea "+" Logged In : "+sessInfo.getEmail());
				return "resetpassword";
			}
			else{
				return "gotoLoginResetPassword";
			}
		}catch (Exception e){
			LOG.debug("check for the exception here" + e.getMessage());
		}
		return "gotoLoginResetPassword";
	}


	@RenderMapping(params = "action=resetPassword" )
	public ModelAndView handlePostRequest(RenderRequest renderRequest,RenderResponse renderResponse, Model model,@Valid @ModelAttribute("reset_password")ResetPassword resetPassword,
			BindingResult result)throws IOException,PortletException,MinervaException {

		boolean value = false;
		try{
			String oldPasswrod = ParamUtil.getString(renderRequest, "oldpswd");
			String newPasswrod = ParamUtil.getString(renderRequest, "newpswd");
			String confirmPasswrod = ParamUtil.getString(renderRequest, "confirmpswd");
			
			validator.validate(resetPassword,result);
			if (result.hasErrors()) {
				LOG.info("validation  failed");
			}
			else{
				LOG.info("validation  Success");
				if(newPasswrod.equalsIgnoreCase(confirmPasswrod)){
					LOG.info("New Password And Confirm Password Matched");
					PortletSession newSession = renderRequest.getPortletSession();
					SessionInfo sessInfo = (SessionInfo)newSession.getAttribute("sessionInfo",PortletSession.APPLICATION_SCOPE);

					String loginuseremail = sessInfo.getEmail();
					LOG.info("Old Password"+oldPasswrod+"\n New Password"+newPasswrod+"\n Confirm Password"+confirmPasswrod);
					value=ideamgmtService.ResetPassword(loginuseremail,oldPasswrod,confirmPasswrod);

					if(value){
						
						SessionMessages.add(renderRequest, "success");
						return new ModelAndView("resetpassword");
					}
					else{
						hideDefaultErrorMessage(renderRequest, renderResponse);
						//display error message
						SessionErrors.add(renderRequest, "wrong_old_pswd");
						return new ModelAndView("resetpassword");
					}
				}
				else{
					hideDefaultErrorMessage(renderRequest, renderResponse);
					//display error message
					SessionErrors.add(renderRequest, "pswd_not_matched");
				}
			}
		}catch (MinervaException me) {
			LOG.error("Reset Password Errors"+me.getStackTrace());
			LOG.debug("check for the exception here" + me.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Reset Password Errors"+e.getStackTrace());
			LOG.debug("check for the exception here" + e.getMessage());
		}
		return new ModelAndView("resetpassword");
	}

	public void  hideDefaultErrorMessage(RenderRequest renderRequest,RenderResponse  renderResponse){
		// Hide default error message
		SessionErrors.add(renderRequest, "error-key");
		SessionMessages.add(renderRequest, PortalUtil.getPortletId(renderRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
	}


}
