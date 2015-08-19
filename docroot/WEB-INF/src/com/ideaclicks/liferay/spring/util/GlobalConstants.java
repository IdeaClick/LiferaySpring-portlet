package com.ideaclicks.liferay.spring.util;

/**
 * Application level constants declared here.
 * 
 * @author Amol Shirude
 * 
 */
public class GlobalConstants {
    /**
     *Constructor.
     */
	GlobalConstants() {
    }

    /**
     * LINE SEPERATOR.
     */
    public static final String LINESEPARATOR = "\n";

    /**
     * QUESTION MARK.
     */
    public static final String QUESTIONMARK = "?";

    /**
     * EQUAL.
     */
    public static final String EQUAL = "=";
    
    /**
     * HASH.
     */
    public static final String HASH = "#";
    
    /**
     * Constant ORGCODE.
     */
    public static final String ORGCODE = "orgcode";
    
    
    
    
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
    
    public static final String LOGIN_URL = "http://localhost:8080/group/liferay/login";
    
    /*
     * Service Success/Error Messages
     */
    public static final String SUCCESS = "success";
    
    public static final String FAILED = "fail";

    
    public static final String REGISTRATION_COMPLETE = "Registration Complete";
    
    public static final String REPEATED_ORGANIZATION_CODE = "Repeated Organization Code"; 
    
    public static final String REPEATED_ORGANIZATION_EMAIL = "Repeated Organization Email";
    
    /*
     * Error Key success/failure
     */
    public static final String ERROR = "error";
    
    public static final String ERROR1 = "error1";

}
