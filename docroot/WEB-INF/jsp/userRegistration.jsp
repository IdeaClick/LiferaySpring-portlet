<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@page import="com.liferay.portal.kernel.captcha.CaptchaTextException"%>
<%@page import="com.liferay.portal.kernel.captcha.CaptchaMaxChallengesException"%>
<liferay-theme:defineObjects />
<portlet:defineObjects/>

<!-- This URL will be invoked before showing the CAPTCHA image -->
<portlet:resourceURL id="captchaURL" var="captchaURL"
								escapeXml="false">
							</portlet:resourceURL>

<!-- This URL validate the CAPTCHA data entered by user -->
<portlet:actionURL var="userRegURL">
	<portlet:param name="action" value="userReg" />
</portlet:actionURL> 

<liferay-ui:success key="success" message="Registration Sucessful" />
<liferay-ui:error key="error" message="Sorry,user already registered" />

<liferay-ui:error key="errorMessage" message="Enter correct data as shown in the image"/>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	  <script src="${pageContext.request.contextPath}/js/aui-min.js"></script>
      <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"></link>
</head>
<body>
<h4>User Registration</h4>
<form:form name="user_reg" modelAttribute="user_reg" method="post"  action="<%=userRegURL%>" >

			<div class="box" style="margin-left: 108px">Organization Name<b style="color: red;">*</b>:<br> 
				<input name="<portlet:namespace />orgName" id="name" type="text" style="height: 30px; width: 260px" required>
			</div><!-- end of box -->

			<div class="box" style="margin-left: 108px">User Name<b style="color: red;">*</b>:<br> 
				<input name="<portlet:namespace />userName" id="name" type="text" title="Enter Organization Name" style="height: 30px; width: 260px" required>
			</div><!-- end of box -->

			<div class="box" style="margin-left: 108px">E-mail<b style="color: red;">*</b>:<br>
				<input name="<portlet:namespace />email" id="email" title="Enter proper Email-Id" type="email" style="height: 30px; width: 260px" required>
			</div><!-- end of box -->
	
			<div class="box" style="margin-left: 108px">Contact No<b style="color: red;">*</b>:<br>
				<input name="<portlet:namespace />contact" id="phno" title="Please enter Contact number" type="tel" maxlength="10" style="height: 30px; width: 260px" required>
			</div><!-- end of box -->
			<div class="box" style="margin-left: 108px">
							<liferay-ui:captcha url="<%=captchaURL%>" />
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