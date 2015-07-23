<%@ include file="/WEB-INF/jsp/include.jsp" %>
<portlet:defineObjects/>

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

<portlet:renderURL var="loginURL">
<portlet:param name="action" value="add"></portlet:param>
</portlet:renderURL>
<form:form name="login" modelAttribute="login" method="post" action="${loginURL}" >
<div id="main">
	<div class="box" style="margin-left: 108px">UserName:<br>
		<input type="text" name="<portlet:namespace />email" placeholder="Enter Your Email" style="border-radius: 0px 10px 0px 10px; width: 250px">
	</div><!-- end of box -->
	
	<div class="box" style="margin-left: 108px">Password:<br>
		<input type="password" name="<portlet:namespace />pswd" placeholder="Enter Your Password" style="border-radius: 0px 10px 0px 10px; width: 250px">
	</div><!-- end of box -->

	<div class="box" style="margin-left: 108px">Organization Name:<br>
		<input name="<portlet:namespace />orgName" id="orgName" type="text" style="border-radius: 0px 10px 0px 10px; width: 250px" readonly="readonly" value="">
	</div><!-- end of box -->

	<div class="box" style="margin-left: 108px">
		<p><input type="checkbox" name="remember" value="yes"><label for="remember">Remember Me</label></p>
	</div>
   <div class="box" style="margin-left: 108px">
		<P><button type="submit" class="btn btn-success" >login</button></p>
	</div><!-- end of box -->
	<div class="box" style="margin-left: 108px">
	<b><a href="<portlet:renderURL>
					<portlet:param name="action" value="adduser"/>
				</portlet:renderURL>">User Registration |
	</a></b>
	<b><a href="<portlet:renderURL>
					<portlet:param name="action" value="forgetPassword"/>
				</portlet:renderURL>">Forget Password?
	</a></b>
	
	</div>  
</div><!-- end of main -->  
</form:form>
</body>
</html>
