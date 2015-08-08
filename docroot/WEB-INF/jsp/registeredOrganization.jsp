<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<portlet:defineObjects/>
<!DOCTYPE html>
<html>
<head>
		<%-- <script src="${pageContext.request.contextPath}/js/aui-min.js"></script> --%>
     	 <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"></link>

</head>
<body>
<div id="main"> 
    <div id="site_content">  
 <div class="sidebar_container">      
        <div class="sidebar">
          <div class="sidebar_item" style="height:100%; overflow-y: scroll;">
          	<p><marquee direction="up" behavior="scroll" scrollamount="4" >
              <c:forEach items="${OrganizationList}" var="Registration">
    					 <ul><li>${Registration.orgName}</li></ul>
    			</c:forEach>
    		</marquee></p>
           </div><!--close sidebar_item-->
        </div><!--close sidebar-->            
             
       </div><!--close sidebar_container-->  
       </div>
       </div> 
</body>
</html>