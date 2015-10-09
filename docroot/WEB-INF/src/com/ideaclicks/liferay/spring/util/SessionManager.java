package com.ideaclicks.liferay.spring.util;

import java.io.Serializable;

import javax.portlet.ActionRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ideaclicks.liferay.spring.domain.OrganizationRegistration;
import com.liferay.portal.util.PortalUtil;

public class SessionManager implements Serializable{

	//private Logger logger = LogWriterFactory.getLogWriter("session");
	/**
	 * This field holds the logger for this class.
	 */
	private static final Log LOG = LogFactory.getLog(SessionManager.class);

	private static SessionManager moSessionInstance = new SessionManager();

	public SessionManager(){
	}

	public static SessionManager getInstance(){
		return moSessionInstance;
	}

	public void logoutUser(RenderRequest renderRequest,SessionInfo sessInfo){

		String lcmName = " [SessionManager.logoutUser] ";
		String lsesId = null;
		PortletSession session = renderRequest.getPortletSession();
		if(sessInfo!=null){
			LOG.info("Session is not null"+sessInfo);
			session.removeAttribute("sessionInfo", PortletSession.APPLICATION_SCOPE);
			//session.invalidate();
			LOG.info("Now Session is null"+sessInfo);
		}
		else{
			LOG.info("Session is null");
		}
		//session.invalidate();
	}
	public void createSession(ActionRequest actonRequest,OrganizationRegistration reg){

		String lcmName = " [SessionManager.createSessions] ";
		String lsesId = null;
		String clientIp  = null;

		clientIp = PortalUtil.getHttpServletRequest(actonRequest).getRemoteAddr();

		if(LOG.isDebugEnabled()){
			LOG.debug(lcmName+"clientIp  = " + clientIp);
		}
		PortletSession newsession = actonRequest.getPortletSession();
		LOG.debug(lcmName+"old session id "+newsession.getId());
		if(!newsession.isNew())
		{
			LOG.debug(lcmName+"inside (not new) session.getId()");
			//session.invalidate();
			LOG.debug(lcmName+"inside  session.invalidate()");

			newsession = actonRequest.getPortletSession();

			LOG.debug(lcmName+"new session created is "+newsession.getId()); 			   
		}

		SessionInfo sessInfo = new SessionInfo();
		sessInfo.setSessionId(newsession.getId());
		sessInfo.setEmail(reg.getEmail()); 
		sessInfo.setUsertype(reg.getUsertype());
		sessInfo.setOrgCode(reg.getOrgCode());
		sessInfo.setRequestIp(clientIp);
		
		System.out.println("Session Id"+newsession.getId()+"email"+reg.getEmail()+"orgcode"+reg.getOrgCode()+"Client Ip"+clientIp+"UserType:"+reg.getUsertype());

		LOG.info("Session Id"+newsession.getId()+"email"+reg.getEmail()+"orgcode"+reg.getOrgCode()+"Client Ip"+clientIp);

		newsession.setAttribute("sessionInfo",sessInfo,PortletSession.APPLICATION_SCOPE);

		LOG.info("SessionInfo"+sessInfo);

	}
}