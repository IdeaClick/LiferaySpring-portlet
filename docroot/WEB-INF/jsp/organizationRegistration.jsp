<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<portlet:renderURL var="OrgRegURL">
	<portlet:param name="action" value="orgRegistration" />
</portlet:renderURL>

<liferay-ui:success key="success"
	message="Registration Sucessful check your email" />
<liferay-ui:error key="error"
	message="Sorry,Organization already registered with entered Organization Code" />
<liferay-ui:error key="error1"
	message="Sorry,Organization already registered with entered Email Id" />
<liferay-ui:error key="captcha" message="Captcha not verified" />

<%@ include file="/WEB-INF/jsp/header.jsp"%>

<body onload="document.login.orgName.focus();">
	<div class="box" style="margin-left: auto; margin-right: auto;">
		<b><fmt:message key="heading.orgregistration" /></b>
	</div>
	<hr>
	<div id="main">
		<c:set var="portletnamespace"
			value="<%=renderResponse.getNamespace()%>" />
		<form:form name="reg" modelAttribute="reg" method="post"
			action="<%=OrgRegURL%>">

			<div class="form-error-message">
				<form:errors path="orgName"></form:errors>
			</div>
			<div class="box" style="margin-left: auto; margin-right: auto;">
				<fmt:message key="label.organizationname" />
				<b style="color: red;">*</b>:<br>
				<form:input name="<portlet:namespace />orgName" path="orgName"
					title="Enter Organization Name" cssErrorClass="form-error-field"
					style="height: 30px; width: 300px" />
			</div><!-- end of box -->
			
			<div class="form-error-message">
				<form:errors path="orgCode"></form:errors>
			</div>
			<div class="box" style="margin-left: auto; margin-right: auto;">
				<fmt:message key="label.organizationcode" />
				<b style="color: red;">*</b>:<br>
				<form:input name="<portlet:namespace />orgCode" path="orgCode"
					title="Enter Organization Code" cssErrorClass="form-error-field"
					style="height: 30px; width: 300px" />
				<span style="height:10px;width:10px;font-weight:bold;" title="Define Org Code. e.g:- <your org name><your city>">?</span>						
			</div><!-- end of box -->
			
			<div class="form-error-message">
				<form:errors path="orgType"></form:errors>
			</div>
			<div class="box" style="margin-left: auto; margin-right: auto;">
				<fmt:message key="label.organizationtype" />
				<b style="color: red">*</b>:<br>
				<form:select id="dept" name="<portlet:namespace />orgType"
					path="orgType" cssErrorClass="form-error-field"
					style="height: 30px; width: 300px">
					<form:option value="">Select Type</form:option>
					<form:option value="Institute">Institution</form:option>
					<form:option value="Corporate">Corporate</form:option>
				</form:select>
			</div><!-- end of box -->
			
			<div class="form-error-message">
				<form:errors path="email"></form:errors>
			</div>
			<div class="box" style="margin-left: auto; margin-right: auto;">
				<fmt:message key="label.email" />
				<b style="color: red;">*</b>:<br>
				<form:input name="<portlet:namespace />email" path="email"
					title="Enter proper Email-Id" cssErrorClass="form-error-field"
					style="height: 30px; width: 300px" />
			</div><!-- end of box -->
			
			<div class="form-error-message"><form:errors path="pswd"></form:errors></div>
			<div class="box" style="margin-left: auto; margin-right: auto;"><fmt:message key="label.password" /><b style="color: red;">*</b>:<br>
				<form:input type="password"  id="password"  name="<portlet:namespace />pswd" path="pswd" title="Please enter Password" style="height: 30px; width: 300px" cssErrorClass="form-error-field"/>
			</div><!-- end of box -->
			
			<div class="form-error-message"><form:errors path="cpswd"></form:errors></div>
			<div class="box" style="margin-left: auto; margin-right: auto;"><fmt:message key="label.confirmPassword" /><b style="color: red;">*</b>:<br>
				<form:input type="password" id="confirm_password" name="<portlet:namespace />cpswd" path="cpswd" title="Please Reenter Password" style="height: 30px; width: 300px" cssErrorClass="form-error-field"/>
			</div><!-- end of box -->
			
			<div class="form-error-message">
				<form:errors path="contact"></form:errors>
			</div>
			
			<div class="box" style="margin-left: auto; margin-right: auto;">
				<fmt:message key="label.contact" />:<br>
				<form:input name="<portlet:namespace />contact" path="contact"
					title="Please enter Contact number" cssErrorClass="form-error-field"
					style="height: 30px; width: 300px" />
			</div><!-- end of box ## maxlength="10"-->
			
			<div style="margin-left: auto; margin-right: auto;"
				class="g-recaptcha"
				data-sitekey=<fmt:message key="captcha.data-sitekey" />>
			</div><!-- end of box -->
			<br>
			<div id="box" style="margin-left: auto; margin-right: auto;">
				<form:button type="submit" style="width:150px">
					<fmt:message key="button.registerorganization" />
				</form:button>
			</div><!-- end of box -->
			
		</form:form>
	</div>
	<!-- end of main -->
</body>
</html>