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
		PortletSession session = actonRequest.getPortletSession();
		LOG.debug(lcmName+"old session id "+session.getId());
		if(!session.isNew())
		{
			LOG.debug(lcmName+"inside (not new) session.getId()");
			//session.invalidate();
			LOG.debug(lcmName+"inside  session.invalidate()");

			session = actonRequest.getPortletSession();

			LOG.debug(lcmName+"new session created is "+session.getId()); 			   
		}

		SessionInfo sessInfo = new SessionInfo();
		sessInfo.setSessionId(session.getId());
		sessInfo.setEmail(reg.getEmail()); 
		sessInfo.setOrgCode(reg.getOrgCode());
		sessInfo.setRequestIp(clientIp);

		LOG.info("Session Id"+session.getId()+"email"+reg.getEmail()+"orgcode"+reg.getOrgCode()+"Client Ip"+clientIp);

		session.setAttribute("sessionInfo",sessInfo,PortletSession.APPLICATION_SCOPE);

		LOG.info("SessionInfo"+sessInfo);

	}

	/*public SessionInfo validateSession(HttpServletRequest request){

		String lcmName = " [SessionManager.validateSession] ";
		SessionInfo sessInfo = null;
		String sessionId = null;//22953_CHG001 
		String clientIp  = null;//22953_CHG001 

		HttpSession session = request.getSession(false);

		if( session == null ){
			logger.logError(lcmName,"Session invalid ");
		}else{
			//22953_CHG001 - Start
			sessInfo = (SessionInfo)session.getAttribute("sessionInfo");
			if(sessInfo == null)
				return null;
			sessionId = session.getId();
			//EPMS_945
			clientIp  = request.getRemoteAddr();
			// OWASP_CODE_UPGRADE_21 starts
			 Validating the userAgent value with sessionInfo value.  It validates the Browser version as well as the OS value. 
			String userAgent = request.getHeader("user-agent");
			logger.logError(lcmName,"userAgent ="+userAgent+"sessInfo.userAgent==>"+sessInfo.userAgent);
			if (userAgent == null || !userAgent.equals(sessInfo.userAgent)){
				logger.logInfo(lcmName,"User Agent is mismatched with session value. Session Value: " + sessInfo.userAgent + " -- Actual Value: "+userAgent);
				return null;
			}	
			// OWASP_CODE_UPGRADE_21 ends
			logger.logError(lcmName,"Session =  " + sessionId + ", clientIp =  " + clientIp);
			logger.logError(lcmName,"Session sessionId =  " + sessInfo.sessionId + ", Session clientIp =  " + sessInfo.requestIp);
			if(sessionId.equals(sessInfo.sessionId) && clientIp.equals(sessInfo.requestIp)){
				logger.logError(lcmName,"Session valid ");				
			}else {
				logger.logError(lcmName,"Session invalid, Ip/Id validation fail ");
				return null;
			}
			//22953_CHG001 - End
		}

		return(sessInfo );
	}

	public  void logoutAll(ServletContext mSvrCtx,String status){

		String lcmName = new String(" [SessionManager.logoutAll] ");

		try{

			logger.logDebug(lcmName,"sending logoutAll ");
    		//ArrayList sessionList=(ArrayList)mSvrCtx.getAttribute("sessionList");
			HashMap sessionList=(HashMap)mSvrCtx.getAttribute("sessionList");
			Iterator iterSession =sessionList.keySet().iterator();

			String lsesId = null;
			String userNo=null;

		    HttpSession session = null;

		    logger.logDebug(lcmName,"sending logoutAll sessionList before"+sessionList);

			while(iterSession.hasNext()){
			userNo=iterSession.next().toString();
			session = (HttpSession)sessionList.get(userNo);
			//Onsite Changes
				if(session !=null)
				{
					try{
						session.invalidate();
					}catch(Exception e){
						logger.logDebug(lcmName,"Exception while invalidating session "+e);
					}
				}
			logger.logDebug(lcmName,"sending logoutAll userNo"+userNo);
			//sessionList.remove(userNo);  //CHG001				
			}
			mSvrCtx.setAttribute("sessionList",new HashMap());//CHG001		

			int sessionSize=sessionList.size();
			logger.logDebug(lcmName,"Total size available in session==> "+sessionSize);
			for(int i=0; i<sessionSize;i++){
				logger.logDebug(lcmName,"sending logoutAll sessionSize "+sessionList.size()+"\t\ti value\t"+i);
				sessionList.remove(i);
			}
			logger.logDebug(lcmName,"Before clearing session List==> ");
			sessionList.clear();
			logger.logDebug(lcmName,"After clearing session List Size is==> "+sessionList);
			logger.logDebug(lcmName,"sending logoutAll sessionList after"+sessionList);
			mSvrCtx.setAttribute("sessionList",sessionList);
			logger.logDebug(lcmName,"After setting the sessionList value ==>"+sessionList);
			logger.logDebug(lcmName,"Retrieving sessionList value setted in Session ==>"+mSvrCtx.getAttribute("sessionList"));
			//Onsite Changes

		status="success";
		}
		catch(Exception p_ex){

			status="failure";
			logger.logError(lcmName,"SessionManager",Utility.getPrintStackTrace(p_ex));
		}

	}



	public  void removeContextUser(ServletContext mSvrCtx,HttpServletRequest request){

		String lcmName = new String(" [SessionManager.logoutAll] ");
        logger.logEntry(lcmName,"Entered this method");
		try{
		//SessionInfo rSessionInfo =validateSession(request);
		//String userNo=request.getParameter("userNo");

		//ArrayList sessionList=(ArrayList)mSvrCtx.getAttribute("sessionList");
		HashMap sessionList=(HashMap)mSvrCtx.getAttribute("sessionList");

		SessionInfo lSessionInfo =null;
		lSessionInfo = validateSession(request);
			if(lSessionInfo !=null){
			String userNo =lSessionInfo.userNo;
			sessionList.remove(userNo);
			logger.logDebug(lcmName,"UserNo"+userNo+" removed from the context");
			}


		//int sessionSize=sessionList.size();

		for(int i=0; i<sessionSize;i++){
			HttpServletRequest req=(HttpServletRequest)sessionList.get(i);
			SessionInfo lSessionInfo =null;
			lSessionInfo = validateSession(req);
			if(lSessionInfo !=null){
			String userId =lSessionInfo.userNo;
			if(userId.equals(userNo)){
				sessionList.remove(i);
				logger.logEntry(lcmName,"UserNo"+userId+" removed from the context");
				break;

			}

			}

		}
	}
		catch(Exception p_ex){

			logger.logError(lcmName,"SessionManager",Utility.getPrintStackTrace(p_ex));
		}

	}





	public  void logoutPwdUser(ServletContext mSvrCtx,UserValue userValue){

		String lcmName = new String(" [SessionManager.logoutAll] ");

		String userNo=userValue.getUserNo();
		try{
		//ArrayList sessionList=(ArrayList)mSvrCtx.getAttribute("sessionList");

		int sessionSize=sessionList.size();

		for(int i=0; i<sessionSize;i++){
			HttpServletRequest req=(HttpServletRequest)sessionList.get(i);
			SessionInfo lSessionInfo =null;
			lSessionInfo = validateSession(req);
			if(lSessionInfo !=null){
			String userId =lSessionInfo.userNo;
			if(userId.equals(userNo)){
				logoutUser(req);
				logger.logEntry(lcmName,"Calling logoutUser:UserNo"+userId);
			}
			sessionList.remove(i);
			}
			break;
		}
		HashMap sessionList=(HashMap)mSvrCtx.getAttribute("sessionList");
		HttpSession session = (HttpSession)sessionList.get(userNo);
				if(session !=null)
				session.invalidate();

		sessionList.remove(userNo);


		userValue.setTransactionStatus("success");
	}
		catch(Exception p_ex){

			userValue.setTransactionStatus("failure");
			logger.logError(lcmName,"SessionManager",Utility.getPrintStackTrace(p_ex));
		}

	}*/
}