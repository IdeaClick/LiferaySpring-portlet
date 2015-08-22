<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<portlet:defineObjects/>

<portlet:actionURL var="forgetpassAction">
	<portlet:param name="action" value="forgetPassword" />
</portlet:actionURL>

<liferay-ui:error key="error" message="Sorry,Wrong Email Id" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"></link>
	<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css"></link>
</head>
<body>

	<form:form modelAttribute="forgetpassword" commandName="forget_password" method="post" action="<%=forgetpassAction%>">
		<h5 align="center">Forget Password..</h5>
		<div id="main">
			<div class="box" align="center">
				Email Id:&nbsp; &nbsp; &nbsp; 
				<form:input name="<portlet:namespace />email" path="email" placeholder="Enter Your Email-Id" style="height: 30px;width: 250px" />
			</div>
			<!-- end of box -->
			<br> <br>
			<div id="box" align="center">
				<p>
					<form:button type="submit" class="btn btn-success">Submit</form:button>
				</p>
			</div>
			<%-- <a class="btn" href="<portlet:renderURL>
					<portlet:param name="action" value="checkLogin"/>
					</portlet:renderURL>">Login
			</a> --%>
		</div>
		<!-- end of main -->
	</form:form>
</body>
</html>