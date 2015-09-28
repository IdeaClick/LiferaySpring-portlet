<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<portlet:defineObjects />

<portlet:renderURL var="forgetpassAction">
	<portlet:param name="action" value="forgetPasswordAction" />
</portlet:renderURL>

<liferay-ui:error key="error" message="Invalid email Id. Please check and try again." />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"></link>
<link href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet" type="text/css"></link>
</head>
<body>
	<div class="box" style="margin-left: auto; margin-right: auto;">
		<b><fmt:message key="heading.forgetpassword" /></b>
	</div>
	<br>
	<form:form modelAttribute="forgetpassword" commandName="forgetpassword"
		method="post" action="<%=forgetpassAction%>">

		<div class="box" style="margin-left: auto; margin-right: auto;">
			<fmt:message key="label.email" />
			<br>
			<input type="email" name="<portlet:namespace />email" 
							placeholder="Enter Your Email-Id" style="height: 30px;width: 250px" required/>
			<br>
			<form:button type="submit">Submit</form:button>
		</div>

	</form:form>
</body>
</html>