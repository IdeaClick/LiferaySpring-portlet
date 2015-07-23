package com.ideaclicks.liferay.spring.controller;

import java.io.IOException;
import java.util.Map;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import com.ideaclicks.liferay.spring.exception.MinervaException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideaclicks.liferay.spring.domain.Registration;
import com.ideaclicks.liferay.spring.service.IdeaManagementService;

@Controller("forgetpasswordController")
@RequestMapping("VIEW")
public class ForgetPasswordController    {
	/**
     * This field holds the logger for this class.
     */
    private static final Log LOG = LogFactory.getLog(ForgetPasswordController.class);
    
	@Autowired
	private IdeaManagementService ideamgmtService;
	
	@RenderMapping
	public String forgetPassword(Map<String, Object> map) {
		map.put("forget_password", new Registration());
		return "forget_password";
	}
	
	
	
	/*@ActionMapping(params = "action=forgetPassword")
	public void forgetpass(ActionRequest actionRequest,ActionResponse actionResponse, Model model,@ModelAttribute("forgetpassword") Registration registration, BindingResult result) throws IOException,
			PortletException,MinervaException {
		//System.out.println("result"+ParamUtil.getString(actionRequest,"firstname"));
		String password=null;
		System.out.println("Email Id:"+registration.getEmail());
		try {
				password=ideamgmtService.forgetPassword(registration.getEmail());
					if(!(password == null)){
							System.out.println("Password is:"+password);
					}
					else if(password == null){
							System.out.println("Incorrect EmailId");
						}
		}catch (MinervaException me) {
            // redirected to error page
            LOG.debug("check for the exception here" + me.getMessage());
        } catch (Exception e) {
            // redirected to error page
            LOG.debug("check for the exception here" + e.getMessage());
        }
		//return "login";
	}*/
}
	

