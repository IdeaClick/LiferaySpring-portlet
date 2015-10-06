package com.ideaclicks.liferay.spring.controller;

import java.io.IOException;
import java.util.Map;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ideaclicks.liferay.spring.domain.UserRegistration;
import com.ideaclicks.liferay.spring.util.SendEmail;
import com.ideaclicks.liferay.spring.util.SessionInfo;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;

@Controller("inviteusersController")
@RequestMapping("VIEW")
public class InviteUsersController {
	/**
	 * This field holds the logger for this class.
	 */
	private static final Log LOG = LogFactory.getLog(InviteUsersController.class);

	SendEmail snd = new SendEmail();

	@RenderMapping
	public String inviteUsers(RenderRequest renderRequest, RenderResponse renderResponse, Model model,Map<String, Object> map) throws IOException,PortletException {
		try{
			PortletSession newSession = renderRequest.getPortletSession();
			SessionInfo sessInfo = (SessionInfo)newSession.getAttribute("sessionInfo",PortletSession.APPLICATION_SCOPE);
			if(sessInfo!=null){
				if(sessInfo.getUsertype().equalsIgnoreCase("Admin"))
					return "invite_users";
				else
					return "this_is_admin_page";
			}
			else
				return "gotologin_inviteusers";
		}catch (Exception e){
			LOG.debug("check for the exception here" + e.getMessage());
		}
		return "gotologin_inviteusers";
	}

	@RenderMapping(params = "action=InviteUsers")
	public ModelAndView handlePostRequest(RenderRequest renderRequest, RenderResponse renderResponse, Model model,@ModelAttribute("invite_users") UserRegistration userReg,
			BindingResult result,Map<String, Object> map)throws IOException,PortletException {

		String email = ParamUtil.getString(renderRequest, "email");
		try{
			LOG.info("Invite user email Id"+email);
			System.out.println("Invitation email Id"+email);
			snd.sendEmailInviteUsers(email);
			SessionMessages.add(renderRequest, "success");
			return new ModelAndView("invite_users");
		}catch(Exception e){
			// Hide default error message
			SessionErrors.add(renderRequest, "error-key");
			SessionMessages.add(renderRequest, PortalUtil.getPortletId(renderRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			//display error message
			SessionErrors.add(renderRequest, "error");
			e.printStackTrace();
			LOG.error("Exception " + e.getMessage());
		}	
		return new ModelAndView("invite_users");
	}          

}
