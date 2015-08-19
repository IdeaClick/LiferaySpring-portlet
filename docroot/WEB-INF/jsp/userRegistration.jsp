<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<portlet:defineObjects/>


<!-- This URL validate the CAPTCHA data entered by user -->
<portlet:renderURL var="userRegURL">
	<portlet:param name="action" value="userReg" />
</portlet:renderURL> 


<liferay-ui:success key="success" message="Registration Sucessful" />
<liferay-ui:error key="error" message="Sorry,user already registered" />
<liferay-ui:error key="error1" message="Sorry, Your organization not registered" />
<liferay-ui:error key="captcha" message="Captcha not verified" />

<!DOCTYPE html>
<html>
<head>
<style>
.form-error-field {
	background-color: #FFC;
}

.form-error-message {
	font-weight: bold;
	color: #900;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%--   <script src="${pageContext.request.contextPath}/js/aui-min.js"></script> --%>
    	 <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"></link>
      <script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body onload="document.login.orgCode.focus();">
<h4><fmt:message key="heading.userregistration" /></h4>
<form:form name="user_reg" modelAttribute="user_reg" method="post"  action="<%=userRegURL%>" >

			<div class="box" style="margin-left: 108px"><fmt:message key="label.organizationcode" /> <b style="color: red;">*</b>:<br> 
				<form:input name="<portlet:namespace />orgCode" path="orgCode" cssErrorClass="form-error-field" style="height: 30px; width: 300px" />
				<div class="form-error-message"><form:errors path="orgCode"></form:errors></div>
			</div><!-- end of box -->

			<div class="box" style="margin-left: 108px"><fmt:message key="label.username" /><b style="color: red;">*</b>:<br> 
				<form:input name="<portlet:namespace />userName" path="userName" title="Enter Organization Name" style="height: 30px; width: 300px" cssErrorClass="form-error-field" />
				<div class="form-error-message"><form:errors path="userName"></form:errors></div>
			</div><!-- end of box -->

			<div class="box" style="margin-left: 108px"><fmt:message key="label.email" /><b style="color: red;">*</b>:<br>
				<form:input name="<portlet:namespace />email" path="email" title="Enter proper Email-Id" style="height: 30px; width: 300px" cssErrorClass="form-error-field"/>
				<div class="form-error-message"><form:errors path="email"></form:errors></div>
			</div><!-- end of box -->
	
			<div class="box" style="margin-left: 108px"><fmt:message key="label.contact" /><b style="color: red;">*</b>:<br>
				<form:input name="<portlet:namespace />contact" path="contact" title="Please enter Contact number" style="height: 30px; width: 300px" cssErrorClass="form-error-field"/>
					<div class="form-error-message"><form:errors path="contact"></form:errors></div>
			</div><!-- end of box -->
			
			<div style="margin-left: 108px" class="g-recaptcha" data-sitekey=<fmt:message key="captcha.data-sitekey" />>
			</div><!-- end of box -->

			<div id="box" style="margin-left: 108px">
				<form:button type="submit" style="width:100px" class="btn btn-warning" ><fmt:message key="button.register" /></form:button>
			</div><!-- end of box -->
</form:form>
</body>
</html>