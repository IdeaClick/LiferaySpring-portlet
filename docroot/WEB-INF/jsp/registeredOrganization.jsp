<%@ include file="/WEB-INF/jsp/include.jsp" %>
<portlet:defineObjects/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<div id="main"> 
    <div id="site_content">  
 <div class="sidebar_container">      
        <div class="sidebar">
          <div class="sidebar_item">
              <c:forEach items="${OrganizationList}" var="Registration">
    					 <p><marquee direction="up" behavior="scroll" scrollamount="2" ><ul><li>${Registration.orgName}</li></ul></marquee></p>
    			</c:forEach>
            </div><!--close sidebar_item-->
        </div><!--close sidebar-->            
             
       </div><!--close sidebar_container-->  
       </div>
       </div> 
</body>
</html>