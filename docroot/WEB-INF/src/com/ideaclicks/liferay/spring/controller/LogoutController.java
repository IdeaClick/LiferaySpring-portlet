package com.ideaclicks.liferay.spring.controller;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideaclicks.liferay.spring.domain.Registration;
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
    /**
     * This method handle the request invalidate the session and retrieves to
     * login page.
     * <br/><br/>
     * The @RequestMapping annotation is used to map URLs like '/xxxx.do' onto
     * an entire class or a particular handler method. Typically the type-level
     * annotation maps a specific request path (or path pattern) onto a form
     * controller, with additional method-level annotations 'narrowing' the
     * primary mapping for a specific HTTP method request method ("GET"/"POST")
     * or specific HTTP request parameters.
     * 
     * @param request
     *            Http Servlet Request
     * @param response
     *            Http Servlet Response
     * @throws MinervaException
     * @return ModelView
     */
	
	@RenderMapping(params = "action=logout") 
    public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws MinervaException {
		System.out.println("We r in logout controller");
        request.getSession().removeAttribute("userSession");
        request.getSession().invalidate();
        return new ModelAndView("login");
    }

}
