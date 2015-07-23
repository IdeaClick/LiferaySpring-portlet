<%@ include file="/WEB-INF/jsp/include.jsp" %>


<portlet:defineObjects/>
<portlet:actionURL var="formAction">
	<portlet:param name="action" value="addData" />
</portlet:actionURL>


<!DOCTYPE html >
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/csd-portal.css" rel="stylesheet" type="text/css">
      <link href="/LiferaySpring-portlet/docroot/WEB-INF/css/bootstrap.min.css" rel="stylesheet"></link>
      <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
		<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
		<script type='text/javascript'>
		<script src="/LiferaySpring-portlet/docroot/WEB-INF/js/aui-min.js">
		dwr.engine.setErrorHandler(<portlet:namespace/>_showError);
		var loginFormName = "com.sample.portal.portlet.announcement.RegistrationController.FORM.org_reg";

		function <portlet:namespace/>_submitForm() {
			document.forms["<portlet:namespace/>_OrgRegForm"].submit();
		}
	</script>

<script type="text/javascript">
function Checkarea(val){
	if(val=="others")
		{
	       document.getElementById('area').style.display='block';
	       document.getElementById('add_btn').style.display='block';
		}
	    else
	    	{
	    		document.getElementById('area').style.display='none'; 
     	    	document.getElementById('add_btn').style.display='none';
	    	}
	       
}

function addCombo() {
    var textb = document.getElementById("area");
    var combo = document.getElementById("combo");
     
    var option = document.createElement("option");
    option.text = textb.value;
    option.value = textb.value;
    try {
        combo.add(option, null); //Standard 
    }catch(error) {
        combo.add(option); // IE only
    }
    textb.value = "";
}
<!--
function validate()
{
  alert("Registered Successfully");
}
//-->
</script> 

</head>
<body>
<div id="main">
	<c:set var="portletnamespace" value="<%=renderResponse.getNamespace()%>"/>
	<form:form name="reg" modelAttribute="reg" method="post" onsubmit="return validate()" action="<%= formAction %>">
		
			<div class="box" style="margin-left: 108px">Organization Name<b style="color: red;">*</b>:<br> 
				<input name="<portlet:namespace />orgName" id="name" type="text" title="Enter Organization Name" style=" border-radius: 0px 10px 0px 10px; width: 250px" required>
			</div><!-- end of box -->
			
			<div class="box" style="margin-left: 108px">Organization Type<b style="color: red">*</b>:<br>
				<select id="dept" name="<portlet:namespace />orgType" style=" border-radius: 0px 10px 0px 10px; width: 260px" required>
 			  		<option value="select">Select Type</option>
    				<option value="Institude">Institude</option>
    				<option value="Corporate">Corporate</option>
    			</select>
    		</div><!-- end of box -->
			
			<div class="box" style="margin-left: 108px">E-mail<b style="color: red;">*</b>:<br>
				<input name="<portlet:namespace />email" id="email" title="Enter proper Email-Id" type="email" style=" border-radius: 0px 10px 0px 10px; width: 250px" required>
			</div><!-- end of box -->
	
			<div class="box" style="margin-left: 108px">Contact No<b style="color: red;">*</b>:<br>
				<input name="<portlet:namespace />contactNo" id="phno" title="Please enter Contact number" type="text" maxlength="10" style=" border-radius: 0px 10px 0px 10px; width: 250px" required>
			</div><!-- end of box -->
	
			<div id="box" style="margin-left: 108px">
				<input type="submit" name="submit" class="btn btn-success"  value="Register Organization"/>
			</div><!-- end of box -->


</form:form>
</div><!-- end of main -->  
<script>
       
YUI().use(
          'aui-button',
          function(Y) {
            new Y.ButtonGroup(
              {
                boundingBox: '#myCheckgroup',
                type: 'radio'
              }
            ).render();
          }
        );
       
YUI().use(
          'aui-dropdown',
          function(Y) {
            new Y.Dropdown(
              {
                boundingBox: '#myDropdown',
                trigger: '#myTrigger'
              }
            ).render();
          }
        );
</script>
</body>
</html>