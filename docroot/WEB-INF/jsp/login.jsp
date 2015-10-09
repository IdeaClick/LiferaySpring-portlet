<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page import="com.liferay.portal.util.PortalUtil,javax.portlet.PortletSession,com.liferay.portal.kernel.util.PropsUtil" %>
<%@ page import="javax.portlet.PortletPreferences" %>

<portlet:actionURL var="loginURL">
<portlet:param name="action" value="login"></portlet:param>
</portlet:actionURL>

<%-- <liferay-ui:success key="success" message="Login successful. You can now submit ideas, review ideas and comment on existing ideas." /> --%>
<liferay-ui:error key="error" message="Invalid User Name or Password" />
<liferay-ui:error key="captcha" message="Captcha not verified" />

<body onload="document.login.email.focus();">
<%
 		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
		//out.print(httpReq);
 	   	String orgcode = httpReq.getParameter("orgcode");
 	 	if(null == orgcode){
 	   		orgcode = "";}
	 	  
%>
	
<form:form name="login" modelAttribute="login" method="post" action="${loginURL}" >
<div id="main">
	<div class="box" style="margin-left: auto; margin-right: auto;"><fmt:message key="label.email" /><br>
		<input type="email" name="<portlet:namespace />email" placeholder="Enter Your Email" id="email" style="height: 30px; width: 300px" required>
	</div><!-- end of box -->
	
	<div class="box" style="margin-left: auto; margin-right: auto;"><fmt:message key="label.password" />&nbsp;&nbsp;
	<b><a href="<portlet:renderURL>
					<portlet:param name="action" value="forgetPswd"/>
					<portlet:param name="id" value="commentsOnIdea"/>
				</portlet:renderURL>"><fmt:message key="button.forgetpassword" />
	</a></b><br>
		<input type="password" name="<portlet:namespace />pswd" placeholder="Enter Your Password" id="password" style="height: 30px; width: 300px" required>
	</div><!-- end of box -->

	<div class="box" style="margin-left: auto; margin-right: auto;"><fmt:message key="lable.organizationcode.LoginPage" /><br>
		<input name="<portlet:namespace />orgCode" id="orgName" value="<%=orgcode%>" type="text" style="height: 30px; width: 300px" required>
		<span style="height:10px;width:10px;font-weight:bold;" title="Please refer to your email for organization code or contact administrator.">?</span>
	</div><!-- end of box -->
		
	<div style="margin-left: auto; margin-right: auto;" class="g-recaptcha" data-sitekey=<fmt:message key="captcha.data-sitekey" />>
	</div><!-- end of box -->
										
	<div class="box" style="margin-left: auto; margin-right: auto;">
		<label class="checkbox">
                    <input type="checkbox" value="remember-me" id="remember_me"> Remember me
                </label>
	</div>
   <div class="box" style="margin-left: auto; margin-right: auto;">
		<P><button type="submit" style="width:100px"><fmt:message key="button.login" /></button>
		<b style="margin-left: 25px"><a href="<portlet:renderURL>
					<portlet:param name="action" value="viewUserReg"/>
				</portlet:renderURL>"><fmt:message key="button.newuser" />
		</a></b></P>
	</div><!-- end of box -->
	 
</div><!-- end of main -->  
</form:form>
</body>
</html>
