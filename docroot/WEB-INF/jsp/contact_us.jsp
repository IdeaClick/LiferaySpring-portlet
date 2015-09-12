<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<portlet:renderURL var="ContactUsURL">
	<portlet:param name="action" value="ContactUs" />
</portlet:renderURL>
<liferay-ui:success key="success" message="Thanks for contacting us" />
<liferay-ui:error key="error" message="please contact again" />

<body>
	<div class="box">
		<b><fmt:message key="heading.contactus" /></b>
	</div>
	<hr>
	<div id="main">
		<c:set var="portletnamespace"
			value="<%=renderResponse.getNamespace()%>" />
		<form:form id="myform" name="contactusform"
			modelAttribute="contactusform" method="post" action="${ContactUsURL}">

			<div class="form-error-message">
				<form:errors path="yourName"></form:errors>
			</div>
			<div class="box" style="margin-left: auto; margin-right: auto;">
				<fmt:message key="label.your_name" />
				<b style="color: red;">*</b>:<br>
				<form:input name="<portlet:namespace />yourName" title="Your Name"
					path="yourName" style="height: 100%; width: 100%" />
			</div><!-- end of box -->
			
			<div class="form-error-message">
				<form:errors path="email"></form:errors>
			</div>
			<div class="box" style="margin-left: auto; margin-right: auto;">
				<fmt:message key="label.your_email" />
				<b style="color: red;">*</b>:<br>
				<form:input name="<portlet:namespace />email" title="Your Email"
					path="email" style="height: 100%; width: 100%" />
			</div><!-- end of box -->
			
			<div class="form-error-message">
				<form:errors path="message"></form:errors>
			</div>
			<div class="box" style="margin-left: auto; margin-right: auto;">
				<fmt:message key="label.message" />
				<b style="color: red;">*</b>:<br>
				<form:textarea name="<portlet:namespace />message" path="message"
					title="message" style="width: 100%; height: 150px"></form:textarea>
			</div><!-- end of box -->

			<br>
			<div class="box" style="margin-left: auto; margin-right: auto;">
				<form:button id="submit" style="width:100px" type="submit"
					class="btn btn-danger">
					<fmt:message key="button.submit" />
				</form:button>

			</div>
			<!-- end of box -->
		</form:form>
	</div>
	<!-- end of main -->
</body>
</html>