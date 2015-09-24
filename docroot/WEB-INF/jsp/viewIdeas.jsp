<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@page import="javax.portlet.PortletSession"%>
<liferay-ui:success key="success" message="Idea Submitted" />

<style type="text/css">
.view-idea-container {
	width: 70%;
	float: left;
	display: inline-block;
	clear: both;
	height: auto;
	font-weight: bold;
	/*overflow-y: scroll;
	    height: 600px;*/
}

.idea-container {
	border: solid;
	border-width: 2px;
	border-color: #0094BC;
	border-radius: 10px;
	padding: 10px;
}

.idea-container a.idea-tile {
	font-size: 24px;
	color: #005777;
	font-weight: bold;
	cursor: pointer;
	text-decoration: none;
}

.idea-container .idea-description {
	font-size: 18px;
	color: #1182FA;
	margin-top: 10px;
	height: 60px;
	overflow: hidden;
	max-width: 700px;
}

.idea-container .idea-details-container .category {
	background-color: #CBCBCB;
	color: #FFFFFF;
	padding: 3px;
}

.idea-container .idea-details-container .submit-by {
	color: #0094BC;
}

.categories-container {
	display: inline-block;
	margin-left: 30px;
	width: 200px;
	margin-top: 60px;
	height: auto;
	background-color: #e6e6e6;
	padding-left: 30px;
	padding-bottom: 20px;
	border-radius: 10px;
}

.categories-container .category a {
	cursor: pointer;
	text-decoration: none;
	font-size: 16px;
}

.categories-container .category {
	margin-top: 10px;
}

.search-container {
	padding-left: 40px;
}

.search-container input[type="text"].search-idea {
	width: 75%;
	margin-bottom: 0px;
	border-radius: 0px 10px 0px 10px;
}

.show-idea-container {
	border: solid;
	border-width: 2px;
	border-color: #0094bc;
	border-radius: 10px;
	padding: 10px;
	height: auto;
	font-weight: bold;
}

.show-idea-container .show-idea-description {
	font-size: 18px;
	color: #1182FA;
	margin-top: 10px;
	height: auto;
}

.show-idea-container .show-idea-category {
	background-color: #CBCBCB;
	color: #FFFFFF;
	padding: 3px;
}

.show-idea-container .show-idea-author {
	color: #0094BC;
}

.show-ide-title {
	font-size: 24px;
	color: #005777;
	font-weight: bold;
}
</style>
</head>
<body>
	<div class="box">
		<fmt:message key="heading.ideas" />
	</div>
	<div>
		<form:form id="viewIdea" name="viewIdea" modelAttribute="viewIdea"
			method="post" action="">
			<div class="view-idea-container">
				<!-- <div class="search-container">
	    	Keywords : <input type="text" name="searchIdeas" class="search-idea"/> 
	    	<button class="btn btn-info">Search</button>
    		</div>-->
				<hr>
				<c:forEach items="${IdeasList}" var="Idea">
					<div class="idea-container">
						<a class="idea-tile"
							href="<portlet:renderURL>
								<portlet:param name="action" value="commentsOnIdea"/>
								<portlet:param name="Ideas_id" value="${Idea.id}"/>
							</portlet:renderURL>">${Idea.title}
						</a> <br>
						<div class="idea-description">${Idea.desc}</div>

						<br>
						<div class="idea-details-container">
							<fmt:message key="label.category" />
							<span class="category"> ${Idea.category} </span> <br>
							<fmt:message key="label.submittedby" />
							<span class="submit-by"> ${Idea.submittedBy} </span>

						</div>
					</div>
					<br>
				</c:forEach>
			</div>
			<div class="box">
				<b>Categories</b>
			</div>
			<div class="categories-container">
				<c:forEach items="${categoryList}" var="IdeasCategory">
					<div class="category">
						<a> ${IdeasCategory.category} </a>
					</div>
				</c:forEach>
			</div>
		</form:form>
	</div>
	<!-- end of main -->
</body>
</html>
