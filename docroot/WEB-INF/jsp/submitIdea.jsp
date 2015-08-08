<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<portlet:defineObjects/>

<portlet:renderURL var="submitIdeaURL">
	<portlet:param name="action" value="submitIdea" />
</portlet:renderURL>
<liferay-ui:success key="loginsuccess" message="Login successfully" />
<liferay-ui:success key="success" message="Idea Submitted" />
<liferay-ui:error key="error" message="Sorry,Idea not submitted" />
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	  <%-- <script src="${pageContext.request.contextPath}/js/aui-min.js"></script> --%>
      <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"></link>
</head>
<body>
<h4>Submit Your Ideas</h4>
<hr>
<div id="main">
<form:form id="myform" name="submit_idea" modelAttribute="submit_idea" method="post" action="${submitIdeaURL}">	
	<div class="box" style="margin-left: 108px">Title<b style="color: red;">*</b>:<br> 
		<input name="<portlet:namespace />title" title="Your Idea" id="name" type="text" style="height: 30px; width: 350px" required>
	</div><!-- end of box -->
	
	<div class="box" style="margin-left: 108px">Description<b style="color: red;">*</b>:<br> 
		<textarea name="<portlet:namespace />desc" id="myTextarea" title="Give Description of your Idea" style="width: 350px; height: 200px;" required></textarea>
	</div><!-- end of box -->
	
	<div class="box" style="margin-left: 108px">Select Category<b style="color: red;">*</b>:<br> 
		<select id="category" title="Please select Category" name="<portlet:namespace />category" style="width: 350px" required>
    		<option value="">Select..</option>
    		<c:forEach items="${categoryList}" var="IdeasCategory">
    			<option value="${IdeasCategory.category}">${IdeasCategory.category}</option>
   			</c:forEach>
   		</select>
    </div><!-- end of box -->
		<br>
	<div class="box" style="margin-left: 108px">
		<button id="submit" style="width:100px" type="submit" class="btn btn-warning">Submit</button>&nbsp;&nbsp;
		<button id="reset"  style="width:100px" type="reset" class="btn btn-danger">Reset</button>
	</div><!-- end of box -->        	
</form:form>
</div><!-- end of main -->  
</body>
</html>