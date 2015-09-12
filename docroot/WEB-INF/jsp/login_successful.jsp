<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@ page import="javax.portlet.PortletSession,com.ideaclicks.liferay.spring.util.SessionInfo" %>
<body>
<div class="box" style="margin-left: auto; margin-right: auto;">You are currently logged-in as
<% 
out.print(session.getAttribute("email"));
%>. You can now submit ideas, review ideas and comment on existing ideas...</div>
</body>
</html>