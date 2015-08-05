package com.ideaclicks.liferay.spring.controller;

import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ideaclicks.liferay.spring.exception.MinervaException;


/**
 * This class implements Controller for logout.
 * <br/><br/>
 * The @Controller annotation indicates that a particular class serves the role
 * of a controller. There is no need to extend any controller base class or
 * reference the Servlet API.The basic purpose of the @Controller annotation is
 * to act as a stereotype for the annotated class, indicating its role. The
 * dispatcher will scan such annotated classes for mapped methods, detecting @RequestMapping
 * annotations. To enable auto detection of such annotated controllers, add
 * component scanning in configuration. This is easily achieved by using the
 * spring-context schema as shown in the following XML snippet.
 * &lt;context:component-scan base-package = "com.ideaclicks.sampleproject.web"/&gt;
 * 
 * @author Rohit Jadhav
 */
@Controller("logoutController")
@RequestMapping("VIEW")
public class LogoutController {
			
	@RenderMapping(params = "action=logout") 
    public ModelAndView handleRequest(RenderRequest request, RenderResponse response, Model model) throws MinervaException {
		System.out.println("We r in logout controller");
		PortletSession portletSession=request.getPortletSession();
		portletSession.removeAttribute("orgCode");
        return new ModelAndView("login");
    }

}
