<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<portlet:defineObjects />
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%-- <script src="${pageContext.request.contextPath}/js/aui-min.js"></script> --%>
<link href="http://cdn.alloyui.com/3.0.1/aui-css/css/bootstrap.min.css" rel="stylesheet"></link>
</head>
<body>
	<div id="myCarousel">
		<img src="<%=renderRequest.getContextPath()%>/images/header.jpg"
			style="width: 100%; height: 250px"> <img
			src="<%=renderRequest.getContextPath()%>/images/yourgreatidea.jpg"
			style="width: 100%; height: 250px"> <img
			src="<%=renderRequest.getContextPath()%>/images/share1.png"
			style="width: 100%; height: 250px"> <img
			src="<%=renderRequest.getContextPath()%>/images/share.jpg"
			style="width: 100%; height: 250px">
	</div>

	<script>
		YUI().use('aui-carousel', function(Y) {
			new Y.Carousel({
				contentBox : '#myCarousel',
				height : 250,
				width : 1320
			}).render();
		});
	</script>
</body>
</html>