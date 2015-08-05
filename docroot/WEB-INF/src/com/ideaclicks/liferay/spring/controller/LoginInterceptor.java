package com.ideaclicks.liferay.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import com.ideaclicks.liferay.spring.util.Constants;

/**
 * This class is type of HandlerInterceptorAdapter for session management. This
 * is an Interceptor for all HTTP request to prehandle the request.
 * <br/><br/>
 * Annotation @Component indicates that an annotated class is a "component".
 *            Such classes are considered as candidates for auto-detection when
 *            using annotation-based configuration and classpath scanning.
 * 
 * @author Rohit Jadhav
 * 
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    /**
     * This method prehandle the request to check if session is valid if not
     * redirect to login page.
     * <br/><br/>
     * Annotation @Override  informs the compiler that the element is meant to
     *           override an element declared in a superclass.
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @param handler
     *            Handler Object
     * @return boolean Request prehandle status
     */

    private static final Log LOG = LogFactory.getLog(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        LOG.info("preHandle method call in LoginInterceptor");
        String getUrl = request.getServletPath();

        LOG.info("getUrl ===>" + getUrl);
        if (getUrl.equals(Constants.LOGIN_URL1)
                || getUrl.equals(Constants.LOGIN_URL2)
                || getUrl.equals(Constants.LOGIN_URL3)) {
            return true;
        } else {
            String userSession = (String) WebUtils.getSessionAttribute(request,
                    "userSession");
            if (userSession != null && userSession.equals("valid")) {
                return true;
            } else {
                RedirectView view = new RedirectView("login.servlet", true);
                LOG
                        .info("Redirected to login.servlet Forwarding to controller");
                ModelAndView modelAndView = new ModelAndView(view);
                throw new ModelAndViewDefiningException(modelAndView);
            }
        }
    }

}
