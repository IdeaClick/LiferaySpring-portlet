<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!-- This URL validate the CAPTCHA data entered by user -->
<portlet:renderURL var="userRegURL">
	<portlet:param name="action" value="userReg" />
</portlet:renderURL> 

<liferay-ui:success key="success" message="Registration Sucessful" />
<liferay-ui:error key="error" message="User already registered with this email-ID. You may want to try Login instead" />
<liferay-ui:error key="error1" message="Invalid Organization Code. Please check with your Administrator for valid Organization Code." />
<liferay-ui:error key="captcha" message="Captcha not verified" />

<body onload="document.login.orgCode.focus();">
<div class="box">
<b><fmt:message key="heading.userregistration" /></b>
</div>
<hr>
<form:form name="user_reg" modelAttribute="user_reg" method="post"  action="<%=userRegURL%>" >
			
			<div class="form-error-message"><form:errors path="orgCode"></form:errors></div>
			<div class="box" style="margin-left: auto; margin-right: auto;"><fmt:message key="label.organizationcode" /> <b style="color: red;">*</b>:<br> 
				<form:input name="<portlet:namespace />orgCode" path="orgCode" cssErrorClass="form-error-field" style="height: 30px; width: 300px" />
			</div><!-- end of box -->
		
			<div class="form-error-message"><form:errors path="userName"></form:errors></div>
			<div class="box" style="margin-left: auto; margin-right: auto;"><fmt:message key="label.username" /><b style="color: red;">*</b>:<br> 
				<form:input name="<portlet:namespace />userName" path="userName" title="Enter Organization Name" style="height: 30px; width: 300px" cssErrorClass="form-error-field" />
			</div><!-- end of box -->
		
			<div class="form-error-message"><form:errors path="email"></form:errors></div>
			<div class="box" style="margin-left: auto; margin-right: auto;"><fmt:message key="label.email" /><b style="color: red;">*</b>:<br>
				<form:input name="<portlet:namespace />email" path="email" title="Enter proper Email-Id" style="height: 30px; width: 300px" cssErrorClass="form-error-field"/>
			</div><!-- end of box -->
		
			<div class="form-error-message"><form:errors path="contact"></form:errors></div>
			<div class="box" style="margin-left: auto; margin-right: auto;"><fmt:message key="label.contact" /><b style="color: red;">*</b>:<br>
				<form:input name="<portlet:namespace />contact" path="contact" title="Please enter Contact number" style="height: 30px; width: 300px" cssErrorClass="form-error-field"/>
			</div><!-- end of box -->
			
			<div style="margin-left: auto; margin-right: auto;" class="g-recaptcha" data-sitekey=<fmt:message key="captcha.data-sitekey" />>
			</div><!-- end of box -->
			
			<div id="box" style="margin-left: auto; margin-right: auto;">
				<form:button type="submit" style="width:100px" class="btn btn-danger" ><fmt:message key="button.register" /></form:button>
			</div><!-- end of box -->
</form:form>
</body>
</html>