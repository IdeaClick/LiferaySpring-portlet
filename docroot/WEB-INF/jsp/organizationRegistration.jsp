<%@ include file="/WEB-INF/jsp/header.jsp" %>

<portlet:renderURL var="OrgRegURL">
	<portlet:param name="action" value="orgRegistration" />
</portlet:renderURL>

<liferay-ui:success key="success" message="Registration Sucessful check your email" />
<liferay-ui:error key="error" message="Sorry,Organization already registered with entered Organization Code" />
<liferay-ui:error key="error1" message="Sorry,Organization already registered with entered Email Id" />
<liferay-ui:error key="captcha" message="Captcha not verified" />

<%@ include file="/WEB-INF/jsp/header.jsp"%>

<body onload="document.login.orgName.focus();">
<h4><fmt:message key="heading.orgregistration" /> </h4>
<div id="main">
	<c:set var="portletnamespace" value="<%=renderResponse.getNamespace()%>"/>
	<form:form name="reg" modelAttribute="reg" method="post" action="<%=OrgRegURL%>">
		
			<div class="box" style="margin-left: 108px"><fmt:message key="label.organizationname" /><b style="color: red;">*</b>:<br>
				<form:input name="<portlet:namespace />orgName" path="orgName" title="Enter Organization Name" cssErrorClass="form-error-field" style="height: 30px; width: 300px" />
				<div class="form-error-message"><form:errors path="orgName"></form:errors></div>
			</div><!-- end of box -->
			
			<div class="box" style="margin-left: 108px"><fmt:message key="label.organizationcode" /><b style="color: red;">*</b>:<br> 
				<form:input name="<portlet:namespace />orgCode" path="orgCode" title="Enter Organization Code" cssErrorClass="form-error-field" style="height: 30px; width: 300px" />
				<div class="form-error-message"><form:errors path="orgCode"></form:errors></div>
			</div><!-- end of box -->
			
			
			<div class="box" style="margin-left: 108px"><fmt:message key="label.organizationtype" /><b style="color: red">*</b>:<br>
				<form:select id="dept" name="<portlet:namespace />orgType" path="orgType" cssErrorClass="form-error-field" style="height: 30px; width: 300px" >
 			  		<form:option value="">Select Type</form:option>
    				<form:option value="Institude">Institution</form:option>
    				<form:option value="Corporate">Corporate</form:option>
    			</form:select>
    			<div class="form-error-message"><form:errors path="orgType"></form:errors></div>
    		</div><!-- end of box -->
			
			<div class="box" style="margin-left: 108px"><fmt:message key="label.email" /><b style="color: red;">*</b>:<br>
				<form:input name="<portlet:namespace />email" path="email" title="Enter proper Email-Id" cssErrorClass="form-error-field" style="height: 30px; width: 300px"/>
				<div class="form-error-message"><form:errors path="email"></form:errors></div>
			</div><!-- end of box -->
	
			<div class="box" style="margin-left: 108px"><fmt:message key="label.contact" /><b style="color: red;">*</b>:<br>
				<form:input name="<portlet:namespace />contactNo" path="contactNo" title="Please enter Contact number" pattern="[0-9]{10}"  maxlength="10" cssErrorClass="form-error-field" style="height: 30px; width: 300px"/>
					<div class="form-error-message"><form:errors path="contactNo"></form:errors></div>
			</div><!-- end of box -->
	
			<div style="margin-left: 108px" class="g-recaptcha" data-sitekey=<fmt:message key="captcha.data-sitekey" />>
			</div><!-- end of box -->

			<div id="box" style="margin-left: 108px">
				<form:button type="submit" style="width:150px" class="btn btn-warning" ><fmt:message key="button.registerorganization" /></form:button>
			</div><!-- end of box -->
</form:form>
</div><!-- end of main -->  
</body>
</html>