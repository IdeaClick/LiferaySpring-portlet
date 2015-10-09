<%@page import="javax.portlet.RenderRequest"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@page import = "javax.portlet.PortletSession"%>
<body>
<body>
    <div class="box" style="margin-left: auto; margin-right: auto;"> <fmt:message key="registration_success.message" />
    <% 
       out.print(session.getAttribute("email"));
    %>
    ..Login <a href="<fmt:message key="login.url"/>">here</a>
    </div>
</body>
</body>
</html>