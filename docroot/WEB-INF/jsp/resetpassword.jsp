<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<portlet:renderURL var="resetPasswordURL">
	<portlet:param name="action" value="resetPassword" />
</portlet:renderURL>
<liferay-ui:success key="success" message="Password reset successful" />
<liferay-ui:error key="wrong_old_pswd"
	message="This is not your old password" />
<liferay-ui:error key="pswd_not_matched"
	message="New Password and Confirm password not same" />

<body>
	<div class="box">
		<b><fmt:message key="heading.resetpassword" /></b>
	</div>
	<hr>
	<form:form id="reset_password_form" name="resetPassword"
		modelAttribute="reset_password" method="post"
		action="${resetPasswordURL}">

		<div class="form-error-message">
			<form:errors path="oldpswd"></form:errors>
		</div>
		<div class="box" style="margin-left: auto; margin-right: auto;">
			<fmt:message key="label.reset.password.old.password" />
			<b style="color: red;">*</b>:<br>
			<form:password  name="<portlet:namespace />oldpswd"
				title="Old Password" path="oldpswd"
				style="height: 100%; width: 30%;" ></form:password>
		</div>
		<!-- end of box -->
		<br>

		<div class="form-error-message">
			<form:errors path="newpswd"></form:errors>
		</div>
		<div class="box" style="margin-left: auto; margin-right: auto;">
			<fmt:message key="label.reset.password.new.password" />
			<b style="color: red;">*</b>:<br>
			<form:password  name="<portlet:namespace />newpswd"
				title="New Password" path="newpswd"
				style="height: 100%; width: 30%;" ></form:password>
		</div>
		<!-- end of box -->
		<br>

		<div class="form-error-message">
			<form:errors path="confirmpswd"></form:errors>
		</div>
		<div class="box" style="margin-left: auto; margin-right: auto;">
			<fmt:message key="label.reset.password.confirm.password" />
			<b style="color: red;">*</b>:<br>
			<form:password name="<portlet:namespace />confirmpswd"
				title="Confirm Password" path="confirmpswd"
				style="height: 100%; width: 30%;" ></form:password>
		</div>
		<!-- end of box -->
		<br>

		<div class="button" style="margin-left: auto; margin-right: auto;">
			<form:button id="submit" style="width:120px" type="submit">
				<fmt:message key="button.reset.password" />
			</form:button>
		</div>
		<br>
		<!-- end of box -->
	</form:form>
</body>
</html>