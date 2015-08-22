<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<liferay-theme:defineObjects />
<portlet:defineObjects/>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.form-success-message {
	font-weight: bold;
	color: #900;
}

</style>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<%-- <script src="${pageContext.request.contextPath}/js/aui-min.js"></script>--%>
      <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"></link> 
</head>
<body>
<body>
    <div class="form-success-message"><fmt:message key="success.message" /></div>
</body>
</body>
</html>