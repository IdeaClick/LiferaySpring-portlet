<%@ include file="/WEB-INF/jsp/include.jsp"%>
<portlet:defineObjects />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<div class="box" style="margin-right: 108px">
		<a
			href="<portlet:renderURL>
					<portlet:param name="action" value="logout"/>
					</portlet:renderURL>">Logout</a>
	</div>
</body>
</html>