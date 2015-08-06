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
<link href="${pageContext.request.contextPath}/css/csd-portal.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"></link>
<script type='text/javascript'>
		<script src="${pageContext.request.contextPath}/js/aui-min.js">	
      </script>
</head>
<body>

	<c:set var="portletnamespace"
		value="<%=renderResponse.getNamespace()%>" />
	<form:form modelAttribute="forgetpassword" commandName="forget_password" method="post" action="<%=forgetpassAction%>">
		<h5 align="center">Forget Password..</h5>
		<div id="main">
			<div class="box" align="center">
				Email Id:&nbsp; &nbsp; &nbsp; 
				<input type="email" name="<portlet:namespace />email" placeholder="Enter Your Email-Id" style="height: 30px;width: 250px" required>
			</div>
			<!-- end of box -->
			<%--  				Email Id: &nbsp; &nbsp;<input type="text" name="<portlet:namespace/>email" id="email" class="form-control" style="width:250px" required>
 --%>
			<br> <br>
			<div id="box" align="center">
				<p>
					<button type="submit" class="btn btn-success">Submit</button>
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