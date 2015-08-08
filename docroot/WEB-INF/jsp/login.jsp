<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<portlet:defineObjects/>

<portlet:renderURL var="loginURL">
<portlet:param name="action" value="login"></portlet:param>
</portlet:renderURL>

<liferay-ui:success key="success" message="Sucessfully Login" />
<liferay-ui:error key="error" message="Invalid User Name or Password" />
<liferay-ui:error key="captcha" message="Captcha not verified" />

<!DOCTYPE html>
    <html lang="en">
    <head>
    <style type="text/css">
		.data, .data td {
			border-collapse: collapse;
			width: 100%;
			border: 1px solid #aaa;
			margin: 2px;
			padding: 2px;
		}
		.data th {
			font-weight: bold;
			background-color: #5C82FF;
			color: white;
		}
</style>
    		 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<%--<link href="${pageContext.request.contextPath}/css/csd-portal.css" rel="stylesheet" type="text/css">
      		<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"></link>
   			<script type='text/javascript'>
					<script src="${pageContext.request.contextPath}/js/aui-min.js">
			</script> --%>
          <!--   <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">  
      		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">  
      
      		<script src="http://code.jquery.com/jquery-1.9.1.js"></script>   
     		 <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>    -->
     		 
     		 <script src='https://www.google.com/recaptcha/api.js'></script>
     		    		    
 </head>
 <body onload="document.login.email.focus();">
<form:form name="login" modelAttribute="login" method="post" action="${loginURL}" >
<div id="main">
	<div class="box" style="margin-left: 108px">Email Id:<br>
		<input type="text" name="<portlet:namespace />email" placeholder="Enter Your Email" id="email" style="height: 30px; width: 300px">
	</div><!-- end of box -->
	
	<div class="box" style="margin-left: 108px">Password:&nbsp;&nbsp;
	<b><a href="<portlet:renderURL windowState="EXCLUSIVE">
					<portlet:param name="action" value="forgetPassword"/>
				</portlet:renderURL>">(Forget Password?)
	</a></b><br>
		<input type="password" name="<portlet:namespace />pswd" placeholder="Enter Your Password" id="password" style="height: 30px; width: 300px" >
	</div><!-- end of box -->

	<div class="box" style="margin-left: 108px">Organization Code:<br>
		<input name="<portlet:namespace />orgCode" id="orgName" type="text" style="height: 30px; width: 300px">
	</div><!-- end of box -->
		
	<div style="margin-left: 108px" class="g-recaptcha" data-sitekey="6LdX4goTAAAAAExl9KqVXKjpmyPodHDrJGOeYo7s">
	</div><!-- end of box -->
										
	<div class="box" style="margin-left: 108px">
		<label class="checkbox">
                    <input type="checkbox" value="remember-me" id="remember_me"> Remember me
                </label>
	</div>
   <div class="box" style="margin-left: 108px">
		<P><button type="submit" style="width:100px" class="btn btn-warning" >login</button>
		<b style="margin-left: 25px"><a href="<portlet:renderURL>
					<portlet:param name="action" value="viewUserReg"/>
				</portlet:renderURL>">New User? 
		</a></b></P>
	</div><!-- end of box -->
	<div class="box" style="margin-left: 108px">
	
	
	
	</div>  
</div><!-- end of main -->  
</form:form>
</body>
</html>
