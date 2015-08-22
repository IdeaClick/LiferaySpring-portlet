<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<portlet:defineObjects />
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"></link>
	<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css"></link>
</head>
<body>
	<div id="main" class="box">
		<div id="site_content">
			<div class="sidebar_container">
				<div class="sidebar">
					<div class="sidebar_item" style="height: 100%; overflow-y: scroll;">
						<p>
							<marquee direction="up" behavior="scroll" scrollamount="4">
								<c:forEach items="${OrganizationList}" var="Registration">
									<ul>
										<li>${Registration.orgName}</li>
									</ul>
								</c:forEach>
							</marquee>
						</p>
					</div>
					<!--close sidebar_item-->
				</div>
				<!--close sidebar-->

			</div>
			<!--close sidebar_container-->
		</div>
	</div>
</body>
</html>