<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<portlet:defineObjects/>


<!-- This URL validate the CAPTCHA data entered by user -->
<portlet:actionURL var="userRegURL">
	<portlet:param name="action" value="userReg" />
</portlet:actionURL> 


<liferay-ui:success key="success" message="Registration Sucessful" />
<liferay-ui:error key="error" message="Sorry,user already registered" />
<liferay-ui:error key="captcha" message="Captcha not verified" />

<!DOCTYPE html>
<html>
<head>
	  <script src="${pageContext.request.contextPath}/js/aui-min.js"></script>
      <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"></link>
      <script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
<h4>User Registration</h4>
<form:form name="user_reg" modelAttribute="user_reg" method="post"  action="<%=userRegURL%>" >

			<div class="box" style="margin-left: 108px">Organization Code<b style="color: red;">*</b>:<br> 
				<input name="<portlet:namespace />orgCode" id="orgCode" type="text" style="height: 30px; width: 300px" required>
			</div><!-- end of box -->

			<div class="box" style="margin-left: 108px">User Name<b style="color: red;">*</b>:<br> 
				<input name="<portlet:namespace />userName" id="userName" type="text" title="Enter Organization Name" style="height: 30px; width: 300px" required>
			</div><!-- end of box -->

			<div class="box" style="margin-left: 108px">E-mail<b style="color: red;">*</b>:<br>
				<input name="<portlet:namespace />email" id="email" title="Enter proper Email-Id" type="email" style="height: 30px; width: 300px" required>
			</div><!-- end of box -->
	
			<div class="box" style="margin-left: 108px">Contact No<b style="color: red;">*</b>:<br>
				<input name="<portlet:namespace />contact" id="phno" title="Please enter Contact number" type="tel" maxlength="10" style="height: 30px; width: 300px" required>
			</div><!-- end of box -->
			
			<div style="margin-left: 108px" class="g-recaptcha" data-sitekey="6LdX4goTAAAAAExl9KqVXKjpmyPodHDrJGOeYo7s">
			</div><!-- end of box -->

			<div id="box" style="margin-left: 108px">
				<input type="submit" style="width:100px" name="submit" class="btn btn-warning"  value="Register"/>
			</div><!-- end of box -->
</form:form>


<script>
YUI().use(
		  'aui-char-counter',
		  function(Y) {
		    new Y.CharCounter(
		      {
		        input: '#myInput',
		        maxLength: 10
		      }
		    );
		  }
		  
		);
YUI().use(
          'aui-form-validator',
          function(Y) {
            new Y.FormValidator(
              {
                boundingBox: '#myForm'
              }
            );
          }
        );

</script>
</body>
</html>