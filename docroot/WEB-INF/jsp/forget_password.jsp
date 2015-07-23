<%@ include file="/WEB-INF/jsp/include.jsp"%>

<portlet:defineObjects />

<portlet:actionURL var="forgetpassAction">
	<portlet:param name="action" value="forgetPassword" />
</portlet:actionURL>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<link href="${pageContext.request.contextPath}/css/csd-portal.css"
	rel="stylesheet" type="text/css">
<link
	href="/LiferaySpring-portlet/docroot/WEB-INF/css/bootstrap.min.css"
	rel="stylesheet"></link>
<script type='text/javascript'
	src='${pageContext.request.contextPath}/dwr/engine.js'></script>
<script type='text/javascript'
	src='${pageContext.request.contextPath}/dwr/util.js'></script>
<script type='text/javascript'>
		<script src="/spring_hibernate-portlet/docroot/js/aui-min.js">
		dwr.engine.setErrorHandler(<portlet:namespace/>_showError);
		
		function <portlet:namespace/>_submitForm() {
			document.forms["<portlet:namespace/>_forgetpassForm"].submit();
		}
      </script>
</head>
<body>

	<c:set var="portletnamespace"
		value="<%=renderResponse.getNamespace()%>" />
	<form:form name="<portlet:namespace/>_forgetpassForm"
		modelAttribute="forgetpassword" commandName="forget_password"
		method="post" action="<%=forgetpassAction%>">
		<h5 align="center">Forget Password..</h5>
		<div id="main">
			<div class="box" align="center">
				Email Id:&nbsp; &nbsp; &nbsp; <input type="text"
					name="<portlet:namespace />email" placeholder="Enter Your Email-Id"
					style="border-radius: 0px 10px 0px 10px; width: 250px">
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