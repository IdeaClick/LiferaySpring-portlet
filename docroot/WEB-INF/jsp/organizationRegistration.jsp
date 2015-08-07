<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<liferay-theme:defineObjects />
<portlet:defineObjects/>

<portlet:actionURL var="formAction">
	<portlet:param name="action" value="orgReg" />
</portlet:actionURL>

<liferay-ui:success key="success" message="Registration Sucessful check your email" />
<liferay-ui:error key="error" message="Sorry,Organization already registered" />
<liferay-ui:error key="captcha" message="Captcha not verified" />

<!DOCTYPE html >
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	  	<script src="${pageContext.request.contextPath}/js/aui-min.js"></script>
      	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"></link>
   		
   		<script type='text/javascript'>
			<script src="${pageContext.request.contextPath}/js/aui-min.js">
		</script>
 		<script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
<h4>Organization Registration</h4>
<div id="main">
	<c:set var="portletnamespace" value="<%=renderResponse.getNamespace()%>"/>
	<form:form name="reg" modelAttribute="reg" method="post" action="<%= formAction %>">
		
			<div class="box" style="margin-left: 108px">Organization Name<b style="color: red;">*</b>:<br> 
				<input name="<portlet:namespace />orgName" id="name" type="text" title="Enter Organization Name" style="height: 30px; width: 300px" required>
			</div><!-- end of box -->
			
			<div class="box" style="margin-left: 108px">Organization Code<b style="color: red;">*</b>:<br> 
				<input name="<portlet:namespace />orgCode" id="name" type="text" title="Enter Organization Code" style="height: 30px; width: 300px" required>
			</div><!-- end of box -->
			
			
			<div class="box" style="margin-left: 108px">Organization Type<b style="color: red">*</b>:<br>
				<select id="dept" name="<portlet:namespace />orgType" style="height: 30px; width: 300px" required>
 			  		<option value="">Select Type</option>
    				<option value="Institude">Institution</option>
    				<option value="Corporate">Corporate</option>
    			</select>
    		</div><!-- end of box -->
			
			<div class="box" style="margin-left: 108px">E-mail<b style="color: red;">*</b>:<br>
				<input name="<portlet:namespace />email" id="email" title="Enter proper Email-Id" type="email" style="height: 30px; width: 300px" required>
			</div><!-- end of box -->
	
			<div class="box" style="margin-left: 108px">Contact No<b style="color: red;">*</b>:<br>
				<input name="<portlet:namespace />contactNo" id="phno" title="Please enter Contact number" type="number" maxlength="10" style="height: 30px; width: 300px" required>
			</div><!-- end of box -->
	
			<div style="margin-left: 108px" class="g-recaptcha" data-sitekey="6LdX4goTAAAAAExl9KqVXKjpmyPodHDrJGOeYo7s">
			</div><!-- end of box -->
	
			<div id="box" style="margin-left: 108px">
				<input type="submit" style="width:150px" name="submit" class="btn btn-warning"  value="Register Organization"/>
			</div><!-- end of box -->


</form:form>
</div><!-- end of main -->  
<script>
       
YUI().use(
          'aui-button',
          function(Y) {
            new Y.ButtonGroup(
              {
                boundingBox: '#myCheckgroup',
                type: 'radio'
              }
            ).render();
          }
        );
       
YUI().use(
          'aui-dropdown',
          function(Y) {
            new Y.Dropdown(
              {
                boundingBox: '#myDropdown',
                trigger: '#myTrigger'
              }
            ).render();
          }
        );
</script>
</body>
</html>