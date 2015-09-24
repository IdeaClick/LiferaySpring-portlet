<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table style="display: table;height:100%;width:100%">
    <tr>
        <td colspan="2">
        <%@ include file="/WEB-INF/jsp/carousel.jsp"%>
        </td>
    </tr>
     <tr>
         <td>
          <%@ include file="/WEB-INF/jsp/home.jsp"%>
        </td>
         <td>
         <%@ include file="/WEB-INF/jsp/registeredOrganization.jsp"%>
        </td>
    </tr>
</table>
</body>
</html>