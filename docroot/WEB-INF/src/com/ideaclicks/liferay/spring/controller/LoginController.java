package com.ideaclicks.liferay.spring.controller;
import java.io.IOException;
import java.util.Map;

import javax.portlet.PortletException;
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
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.ideaclicks.liferay.spring.domain.OrganizationRegistration;
import com.ideaclicks.liferay.spring.service.IdeaManagementService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.ideaclicks.liferay.spring.util.LiferaySessionUtil;

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
	public String login(Map<String, Object> map) {
		map.put("login", new OrganizationRegistration());
		return "login";
	}
	
	@RenderMapping(params = "action=login") 
	public ModelAndView Authentication(RenderRequest renderRequest, RenderResponse renderResponse, Model model, @ModelAttribute("login") OrganizationRegistration reg, BindingResult result) throws IOException,
	PortletException,SecurityException{ 
		boolean value = true;
		try {
			String emailId = ParamUtil.getString(renderRequest,"email");
			String password = ParamUtil.getString(renderRequest,"pswd");
			System.out.println("Email Id:"+emailId);
			
			//value = ideamgmtService.authenticateUser(emailId, password);
			
			if(value){
				System.out.println("Pass:"+password);
				LiferaySessionUtil.setGlobalSessionAttribute("orgCode","tcs@123", renderRequest);
					/*PortletSession session = renderRequest.getPortletSession();
					session.setAttribute("orgCode","tcs@123", PortletSession.APPLICATION_SCOPE);*/
				return new ModelAndView("submitIdea","categoryList",ideamgmtService.getIdeasCategoryList());
			}
			else{
					SessionErrors.add(renderRequest, "error");
				return new ModelAndView("login");
			}
			
			}catch(SecurityException se) {
				LOG.error("SecurityException " + se.getMessage());
				ObjectError error = new ObjectError("InvalidUser","Invalid User name / Password ");
				result.addError(error);
			} catch (Exception e) {
				ObjectError error = new ObjectError("InvalidUser","Invalid User name / Password ");
				result.addError(error);
			}
		
		return new ModelAndView("login");
		}
}	