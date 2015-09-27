package com.ideaclicks.liferay.spring.controller;
import java.io.IOException;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
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
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.ideaclicks.liferay.spring.domain.Ideas;
import com.ideaclicks.liferay.spring.domain.OrganizationRegistration;
import com.ideaclicks.liferay.spring.exception.MinervaException;
import com.ideaclicks.liferay.spring.service.IdeaManagementService;
import com.ideaclicks.liferay.spring.util.SessionInfo;
import com.ideaclicks.liferay.spring.util.SessionManager;
import com.ideaclicks.liferay.spring.util.VerifyRecaptcha;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

@Controller("loginController")
@RequestMapping("VIEW")
public class LoginController extends MVCPortlet  {

	/**
	 * This field holds the logger for this class.
	 */
	private static final Log LOG = LogFactory.getLog(LoginController.class);

	@Autowired
	private IdeaManagementService ideamgmtService;

	@RenderMapping 
	public String login(RenderRequest request, RenderResponse response, Model model,Map<String, Object> map) throws IOException,
	PortletException {		
		try{
			PortletSession newSession = request.getPortletSession();
			SessionInfo sessInfo = (SessionInfo)newSession.getAttribute("sessionInfo",PortletSession.APPLICATION_SCOPE);
			LOG.info("Submit Idea Controller Session Info"+sessInfo);
			if(sessInfo!=null){
				model.addAttribute("submit_idea", new Ideas());
				map.put("categoryList",ideamgmtService.getIdeasCategoryList());
				return "login_successful";
			}
			else{
				return "login";
			}
		}catch (Exception e){
			LOG.debug("check for the exception here" + e.getMessage());
		}
		return "login";
	}

	@RenderMapping(params = "action=loginForm")
	public ModelAndView renderOneMethod(RenderRequest request, RenderResponse response, Model model, @ModelAttribute("login") OrganizationRegistration registration, BindingResult result) throws IOException,
	PortletException,MinervaException {
		return new ModelAndView("login");
	}

	@ActionMapping(params = "action=login") 
	public void Authentication(ActionRequest actionRequest, ActionResponse actionResponse,Model model, @ModelAttribute("login") OrganizationRegistration reg, 
			BindingResult result,Map<String, Object> map) throws IOException,PortletException,SecurityException{ 
		boolean value = true;
		try {

			// get reCAPTCHA request param
			String gRecaptchaResponse = actionRequest.getParameter("g-recaptcha-response");
			System.out.println(gRecaptchaResponse);
			boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
			if(verify){
				String emailId = ParamUtil.getString(actionRequest,"email");
				String password = ParamUtil.getString(actionRequest,"pswd");
				String orgcode =  ParamUtil.getString(actionRequest,"orgCode");
				LOG.info("Email Id"+emailId+"Password"+password+"Organization Code"+orgcode);
			
				value = ideamgmtService.authenticateUser(emailId, password ,orgcode);
				
				if(value){
					LOG.info("Successfully Login");
					ThemeDisplay td  = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
					LOG.info("Home URL"+td.getURLHome());
					System.out.println("User Type Login Controller:"+ideamgmtService.getUserType(emailId));
					reg.setUsertype(ideamgmtService.getUserType(emailId));
					SessionManager ownsessionobject = SessionManager.getInstance();
					ownsessionobject.createSession(actionRequest,reg);
					
					PortletSession session = actionRequest.getPortletSession();
					session.setAttribute("email",emailId, PortletSession.APPLICATION_SCOPE);
					
					SessionMessages.add(actionRequest, "loginsuccess");
					super.processAction(actionRequest, actionResponse);
					
					LOG.info("before retrun submit idea");
								        
					actionResponse.sendRedirect("http://localhost:8081/group/private/submit-idea?p_p_id=Submit_Idea_WAR_IdeaClicksMVPportlet&p_p_lifecycle=0");
					
					//actionResponse.sendRedirect(td.getURLHome()+"/submit-idea?p_p_id=Submit_Idea_WAR_IdeaClicksMVPportlet");
				}
				else{
					// Hide default error message
					SessionErrors.add(actionRequest, "error-key");
					SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
					//display error message
					SessionErrors.add(actionRequest, "error");
				}
			}else{
				LOG.info("Captcha Resopnse"+verify);
				// Hide default error message
				SessionErrors.add(actionRequest, "error-key");
				SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
				//display error message
				SessionErrors.add(actionRequest, "captcha");
			}
		}catch(SecurityException se) {
			LOG.error("SecurityException " + se.getMessage());
			LOG.info("SecurityException" + se.getStackTrace());
			ObjectError error = new ObjectError("InvalidUser","Invalid User name / Password ");
			result.addError(error);
		} catch (Exception e) {
			LOG.info("Exception" + e.getMessage());
			LOG.info("Exception stack" + e.getStackTrace().toString());
			ObjectError error = new ObjectError("InvalidUser","Invalid User name / Password ");
			result.addError(error);
		}
		LOG.info("before retrun login");
	}
}	