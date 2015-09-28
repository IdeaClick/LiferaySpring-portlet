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
	public static final char special_char[]={'<','>','%','"','#','!','~','^','{','}','[',']','|'};
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
	public static final String LOGIN_URL = "http://ideaclicks.in/web/guest/home";
	public static final String SUBMIT_IDEA_URL = "http://ideaclicks.in/web/guest/submit-idea";
	public static final String VIEW_IDEA_URL = "http://ideaclicks.in/web/guest/view-idea";
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
	/*
	 * Email Username and password
	 */
	public static final String EMAIL_USERNAME = "team.ideaclicks@gmail.com";
	public static final String EMAIL_PASSWORD = "gzO9U8EzC9Bv8GKoF+/n8g==";
	/*
	 * Password generator constants 
	 */
	public static final String ALPHA_CAPS = "ZYXWVUTSRQPONMLKJIHGFEDCBA";
	public static final String ALPHA = "zyxwvutsrqponmlkjihgfedcba";
	public static final String NUM = "8765409321";
	public static final String SPL_CHARS = "*_-/#=!@^&+$%";
	public static final int NOOFCAPSALPHA = 2;
	public static final int NOOFDIGITS = 2;
	public static final int NOOFSPLCHARS = 2;
	public static final int MINLEN = 7;
	public static final int MAXLEN = 9;
	
	/*
	 * Captcha keys
	 */
	public static final String URL = "https://www.google.com/recaptcha/api/siteverify";
	public static final String SECRET = "6Lc8JA0TAAAAAOL1RrDK9aaKclAAOkGtdbjPrwO5";
	public final static String USER_AGENT = "Mozilla/5.0";
	public final static String captcha_data_sitekey = "6Lc8JA0TAAAAAKLPLx4GBmwLUaTlDynwUzhuX__M";

}
