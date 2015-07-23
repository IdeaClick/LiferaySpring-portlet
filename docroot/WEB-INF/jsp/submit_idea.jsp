<%@ include file="/WEB-INF/jsp/include.jsp" %>
<portlet:defineObjects/>
<portlet:actionURL var="submitIdeaURL">
	<portlet:param name="action" value="submitIdea" />
</portlet:actionURL>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Submit Ideas</title>
<script src="/LiferaySpring-portlet/docroot/WEB-INF/js/aui-min.js"></script>
<link href="/LiferaySpring-portlet/docroot/WEB-INF/css/bootstrap.min.css" rel="stylesheet"></link>

</head>
<body>
<h3>Submit Idea</h3>
<hr>
<div id="main">
<form:form id="myform" name="submit_idea" modelAttribute="submit_idea" method="post" action="${submitIdeaURL}">	
	<div class="box" style="margin-left: 108px">Title<b style="color: red;">*</b>:
		<input name="<portlet:namespace />title" title="Your Idea" id="name" type="text" style=" width: 350px; margin-left: 53px" required>
	</div><!-- end of box -->
	
	<div class="box" style="margin-left: 108px">Description<b style="color: red;">*</b>: 
		<textarea name="<portlet:namespace />desc" id="myTextarea" title="Give Description of your Idea" style=" height: 100px; width: 350px; margin-left: 10px" required></textarea>
	</div><!-- end of box -->
	
	<div class="box" style="margin-left: 108px">Select Category<b style="color: red;">*</b>:
		<select id="category" title="Please select Category" name="<portlet:namespace />category" style=" width: 150px; margin-left: 30px" required>
    		<option value="selected">Select..</option>
    		<option value="IT">IT</option>
   			<option value="HealthCare">HealthCare</option>
    		<option value="Mobile">Mobile</option>
   		</select>
    </div><!-- end of box -->
		<br>
	<div id="box" style="margin-left: 250px" >
		<button id="submit" type="submit" class="btn">Submit</button>&nbsp;&nbsp;
		<button id="reset"  type="reset" class="btn btn-danger">Reset</button>
	</div><!-- end of box -->        	
</form:form>
</div><!-- end of main -->  
</body>
</html>