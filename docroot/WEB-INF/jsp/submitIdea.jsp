<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<portlet:actionURL var="submitIdeaURL">
	<portlet:param name="action" value="submitIdea" />
</portlet:actionURL>
<liferay-ui:success key="loginsuccess"
	message="Login successful. You can now submit ideas, review ideas and comment on existing ideas." />
<liferay-ui:error key="error" message="Sorry,Idea not submitted" />

<body>
	<div class="box">
		<fmt:message key="heading.submitidea" />
	</div>
	<hr>
	<div id="main">
		<form:form id="myform" name="submit_idea" modelAttribute="submit_idea"
			method="post" action="${submitIdeaURL}">

			<div class="form-error-message">
				<form:errors path="title"></form:errors>
			</div>
			<div class="box" style="margin-left: auto; margin-right: auto;">
				<fmt:message key="label.ideatitle" />
				<b style="color: red;">*</b>:<br>
				<form:input name="<portlet:namespace />title" title="Ideas Title"
					path="title" style="height: 100%; width: 50%;" />
			</div>
			<!-- end of box -->
			<br>
			<div class="form-error-message">
				<form:errors path="desc"></form:errors>
			</div>
			<div class="box" style="margin-left: auto; margin-right: auto;">
				<fmt:message key="label.desc" />
				<b style="color: red;">*</b>:<br>
				<form:textarea name="<portlet:namespace />desc" path="desc"
					title="Give Description of your Idea"
					style="width: 50%; height: 150px;"></form:textarea>
			</div>
			<!-- end of box -->
			<br>
			<div class="form-error-message">
				<form:errors path="category"></form:errors>
			</div>
			<div class="box" style="margin-left: auto; margin-right: auto;">
				<fmt:message key="label.selectcategory" />
				<b style="color: red;">*</b>:<br>
				<form:select path="category" title="Please select Category"
					name="<portlet:namespace />category" style="width: 50%;">
					<form:option value="">Select..</form:option>
					<c:forEach items="${categoryList}" var="IdeasCategory">
						<form:option value="${IdeasCategory.category}">${IdeasCategory.category}</form:option>
					</c:forEach>
				</form:select>
			</div>
			<!-- end of box -->
			<br>
			<div class="box" style="margin-left: auto; margin-right: auto;">
				<form:button id="submit" style="width:100px" type="submit"
					class="btn btn-danger">
					<fmt:message key="button.submit" />
				</form:button>
				&nbsp;&nbsp;
				<form:button id="reset" style="width:100px" type="reset"
					class="btn btn-warning">
					<fmt:message key="button.reset" />
				</form:button>
			</div>
			<!-- end of box -->
		</form:form>
	</div>
	<!-- end of main -->
</body>
</html>