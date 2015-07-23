<%@ include file="/WEB-INF/jsp/include.jsp" %>
<portlet:defineObjects/>

<portlet:actionURL var="userRegURL">
	<portlet:param name="action" value="adduser" />
</portlet:actionURL>
<!DOCTYPE html>
<html>
<head>
<link href="/LiferaySpring-portlet/docroot/WEB-INF/css/bootstrap.min.css" rel="stylesheet"></link>
      <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
		<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
</head>
<body>
<h4>User Registration</h4>
<form:form name="user_reg" modelAttribute="user_reg" method="post"  action="<%=userRegURL%>" >

			<div class="box" style="margin-left: 108px">Organization Name<b style="color: red;">*</b>:<br> 
				<input name="<portlet:namespace />orgName" id="name" type="text" readonly="readonly" style=" border-radius: 0px 10px 0px 10px; width: 250px" required>
			</div><!-- end of box -->

			<div class="box" style="margin-left: 108px">User Name<b style="color: red;">*</b>:<br> 
				<input name="<portlet:namespace />userName" id="name" type="text" title="Enter Organization Name" style=" border-radius: 0px 10px 0px 10px; width: 250px" required>
			</div><!-- end of box -->

			<div class="box" style="margin-left: 108px">E-mail<b style="color: red;">*</b>:<br>
				<input name="<portlet:namespace />email" id="email" title="Enter proper Email-Id" type="email" style=" border-radius: 0px 10px 0px 10px; width: 250px" required>
			</div><!-- end of box -->
	
			<div class="box" style="margin-left: 108px">Contact No<b style="color: red;">*</b>:<br>
				<input name="<portlet:namespace />contact" id="phno" title="Please enter Contact number" type="text" maxlength="10" style=" border-radius: 0px 10px 0px 10px; width: 250px" required>
			</div><!-- end of box -->
	
			<div id="box" style="margin-left: 108px">
				<input type="submit" name="submit" class="btn btn-success"  value="Register"/>
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