<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ page import="javax.portlet.PortletSession,com.ideaclicks.liferay.spring.util.SessionInfo" %>
<title>Logged in user</title>
<body>
	<%
		PortletSession newSession = renderRequest.getPortletSession();
		SessionInfo sessInfo = (SessionInfo) newSession.getAttribute("sessionInfo", PortletSession.APPLICATION_SCOPE);
		if(sessInfo!=null){
			String loginuseremail = sessInfo.getEmail();		%>
		<H5 align="right">Logged In : <%=loginuseremail%></H5>
		<%}%>
 </body>
</html>