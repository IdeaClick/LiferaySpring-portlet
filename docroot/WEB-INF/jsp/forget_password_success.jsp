<%@page import="javax.portlet.RenderRequest"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@page import = "javax.portlet.PortletSession"%>
<body>
<body>
    <div class="box" style="margin-left: auto; margin-right: auto;"><fmt:message key="forget_password_success.message" />
    <% 
       out.print(session.getAttribute("email"));
    
    %>
    </div>
</body>
</body>
</html>