<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ page import="com.liferay.portal.util.PortalUtil,javax.portlet.PortletSession,com.liferay.portal.kernel.util.PropsUtil" %>
<%@ page import="javax.portlet.PortletPreferences" %>

<portlet:renderURL var="loginURL">
<portlet:param name="action" value="login"></portlet:param>
</portlet:renderURL>

<liferay-ui:success key="success" message="Sucessfully Login" />
<liferay-ui:error key="error" message="Invalid User Name or Password" />
<liferay-ui:error key="captcha" message="Captcha not verified" />

<body onload="document.login.email.focus();">
<%
 		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
 	   	String orgcode = httpReq.getParameter("orgcode");
 	 	if(null == orgcode){
 	   		orgcode = "";}
 	 	  
%>
	
<form:form name="login" modelAttribute="login" method="post" action="${loginURL}" >
<div id="main">
	<div class="box" style="margin-left: 108px"><fmt:message key="label.email" /><br>
		<input type="email" name="<portlet:namespace />email" placeholder="Enter Your Email" id="email" style="height: 30px; width: 300px" required>
	</div><!-- end of box -->
	
	<div class="box" style="margin-left: 108px"><fmt:message key="label.password" />&nbsp;&nbsp;
	<b><a href="<portlet:renderURL windowState="EXCLUSIVE">
					<portlet:param name="action" value="forgetPassword"/>
				</portlet:renderURL>"><fmt:message key="button.forgetpassword" />
	</a></b><br>
		<input type="password" name="<portlet:namespace />pswd" placeholder="Enter Your Password" id="password" style="height: 30px; width: 300px" required>
	</div><!-- end of box -->

	<div class="box" style="margin-left: 108px"><fmt:message key="label.organizationcode" /><br>
		<input name="<portlet:namespace />orgCode" id="orgName" value="<%=orgcode%>" type="text" style="height: 30px; width: 300px" required>
	</div><!-- end of box -->
		
	<div style="margin-left: 108px" class="g-recaptcha" data-sitekey=<fmt:message key="captcha.data-sitekey" />>
	</div><!-- end of box -->
										
	<div class="box" style="margin-left: 108px">
		<label class="checkbox">
                    <input type="checkbox" value="remember-me" id="remember_me"> Remember me
                </label>
	</div>
   <div class="box" style="margin-left: 108px">
		<P><button type="submit" style="width:100px" class="btn btn-warning" ><fmt:message key="button.login" /></button>
		<b style="margin-left: 25px"><a href="<portlet:renderURL>
					<portlet:param name="action" value="viewUserReg"/>
				</portlet:renderURL>"><fmt:message key="label.newuser" />
		</a></b></P>
	</div><!-- end of box -->
	<div class="box" style="margin-left: 108px">
	
	
	
	</div>  
</div><!-- end of main -->  
</form:form>
</body>
</html>
