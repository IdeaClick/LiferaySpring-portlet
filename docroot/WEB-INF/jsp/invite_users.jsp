<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<portlet:renderURL var="InviteUsersURL">
	<portlet:param name="action" value="InviteUsers" />
</portlet:renderURL>

<liferay-ui:success key="success" message="Invitation sent" />
<liferay-ui:error key="error" message="Sorry,Idea not submitted" />


<body>
<form:form name="invite_users" modelAttribute="invite_users" method="post" action="${InviteUsersURL}" >
<div id="main">
	<div class="box" style="margin-left: auto; margin-right: auto;"><fmt:message key="label.email" /><br>
		<input type="email" name="<portlet:namespace />email" placeholder="Enter user email" id="email" style="height: 30px; width: 300px" required>
	</div><!-- end of box -->
	
	 <div class="box" style="margin-left: auto; margin-right: auto;">
		<button type="submit" style="width:100px"><fmt:message key="button.invite" /></button>
	</div><!-- end of box -->
	 
</div><!-- end of main -->  
</form:form>
</body>
</html>