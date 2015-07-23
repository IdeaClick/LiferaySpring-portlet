package com.ideaclicks.liferay.spring.base;

/**
 * ApplicationConstants class defines the constants used in the application.
 * 
 * @author asarin
 */
public class ApplicationConstants {
	public static final int ERROR = 1;
	public static final int SUCCESS = 0;
	public static final String PORTLET_ID = "PORTLET_ID";
	public static final String LAYOUT = "LAYOUT";
	public static final String LAYOUT_SEPARATOR = "_LAYOUT_";
	public static final String PORTLET_PACKAGE = "javax.portlet.p";
	public static final String SESSION_ID_SEPARATOR = "?";
	public static final String ANNOUNCEMENT_ADDED_FILE_ROW = "announcement.fileRow";
	public static final String MIME_TYPES = "mime.types";
	
	/** name of message source bean **/
	public static final String MESSAGE_SOURCE_BEAN_NAME = "messageSource";
	
	/** fields related to file downloads **/
	public static final String FILE_TYPE_PROP = "fileType";
	public static final String FILE_ID_PROP = "fileId";
	public static final String ANNOUNCEMENT_FILE_TYPE_VALUE = "announcementFile";
}
