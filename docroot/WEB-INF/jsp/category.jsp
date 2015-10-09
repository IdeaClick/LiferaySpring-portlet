<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<portlet:actionURL var="addCategoryURL">
	<portlet:param name="action" value="addCategory" />
</portlet:actionURL>

<portlet:actionURL var="deleteCategoryURL">
	<portlet:param name="action" value="deleteCategory" />
</portlet:actionURL>

<liferay-ui:success key="success" message="Organization Category Added" />
<liferay-ui:error key="error" message="This Category has already been added" />

<body>
	<div id="main">
		<div class="leftCategoryContianer">
			<form:form id="myform" name="add_category" commandName="add_category"
				modelAttribute="add_category" method="post"
				action="${addCategoryURL}">

				<div class="form-error-message">
					<form:errors path="category"></form:errors>
				</div>
				<div class="box" style="margin-left: auto; margin-right: auto;">
					<fmt:message key="label.addcategory" />
					<b style="color: red;">*</b>:<br>
					<form:input path="category" style="height: 100%; width: 70%;" />
				</div>
				<div class="box" style="margin-top: 10px; margin-bottom: 10px;">
					<form:button id="submit" style="width:100px" type="submit"
						class="button">
						<fmt:message key="button.add" />
					</form:button>
				</div>
			</form:form>
			<div>
				<b><fmt:message key="label.yourorgcategories" /></b>
				<table border="1" class="orgCategoryTable">
					<c:if test="${!empty organizationCategoryList}">
						<c:forEach items="${organizationCategoryList}" var="IdeasCategory">
							<tr>
								<td class="orgCategory">${IdeasCategory.category}</td>
								<td><b><a
										onclick="return confirm('Are you sure you want to delete this category?');"
										href="${deleteCategoryURL}&categoryId=${IdeasCategory.id}"
										class="delete-category">Delete </a></b></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
		</div>

		<div class="rightCategoryContainer">
			<div>
				<b><fmt:message key="label.defaultcategories" /></b>
				<table border="1" class="defaultCategoryTable">
					<c:forEach items="${defaultCategoryList}" var="IdeasCategory">
						<tr>
							<td>${IdeasCategory.category}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<!-- end of main -->
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
</body>
</html>