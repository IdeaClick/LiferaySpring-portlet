<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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