package com.ideaclicks.liferay.spring.util;

/**
 * Application level constants declared here.
 * 
 * @author Amol Shirude
 * 
 */
public class Constants {
    /**
     *Constructor.
     */
    Constants() {
    }

    /**
     * LINE SEPERATOR.
     */
    public static final String LINESEPARATOR = "\n";

    /*
     * error code to set in HttpServletResponse.
     */
    public static final int ERROR_CODE = 412;
    
    /*
     * error code for session expired.
     */
    public static final int SESSION_ERROR_CODE = 406;
    
    /*
     * default error code.
     */
    public static final int DEFAULT_ERROR_CODE = 500;
    
    /*
     * To be avoided URL by Intercepter.
     */
    public static final String LOGIN_URL1 = "/login.jsp";
    
    /*
     * To be avoided URL by Intercepter.
     */
    public static final String LOGIN_URL2 = "/login.jsp";
    
    /*
     * To be avoided URL by Intercepter.
     */
    public static final String LOGIN_URL3 = "/login.jsp";
    
    /*
     * Locked Status Code.
     */
    public static final String LOCKED_STATUS_CODE = "LOCKED";
    

}
