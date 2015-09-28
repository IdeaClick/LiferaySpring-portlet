<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<title>Home</title>
<style type="text/css">
#box .myButton {
	-moz-box-shadow: inset 0px 1px 0px 0px #97c4fe;
	-webkit-box-shadow: inset 0px 1px 0px 0px #97c4fe;
	box-shadow: inset 0px 1px 0px 0px #97c4fe;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #3d94f6
		), color-stop(1, #1e62d0) );
	background: -moz-linear-gradient(top, #3d94f6 5%, #1e62d0 100%);
	background: -webkit-linear-gradient(top, #3d94f6 5%, #1e62d0 100%);
	background: -o-linear-gradient(top, #3d94f6 5%, #1e62d0 100%);
	background: -ms-linear-gradient(top, #3d94f6 5%, #1e62d0 100%);
	background: linear-gradient(to bottom, #3d94f6 5%, #1e62d0 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#3d94f6',
		endColorstr='#1e62d0', GradientType=0 );
	background-color: #3d94f6;
	-moz-border-radius: 16px;
	-webkit-border-radius: 16px;
	border-radius: 16px;
	border: 1px solid #337fed;
	display: inline-block;
	cursor: pointer;
	color: #ffffff;
	font-family: Georgia;
	font-size: 15px;
	font-color: #FFFF00;
	font-weight: bold;
	padding: 19px 42px;
	text-decoration: none;
	text-shadow: 0px 1px 0px #1570cd;
}

#box .myButton:hover {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #1e62d0
		), color-stop(1, #3d94f6) );
	background: -moz-linear-gradient(top, #1e62d0 5%, #3d94f6 100%);
	background: -webkit-linear-gradient(top, #1e62d0 5%, #3d94f6 100%);
	background: -o-linear-gradient(top, #1e62d0 5%, #3d94f6 100%);
	background: -ms-linear-gradient(top, #1e62d0 5%, #3d94f6 100%);
	background: linear-gradient(to bottom, #1e62d0 5%, #3d94f6 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#1e62d0',
		endColorstr='#3d94f6', GradientType=0 );
	background-color: #1e62d0;
}

#box .myButton:active {
	position: relative;
	top: 1px;
}
</style>
</head>
<body>

	<div id="box" align="center">
		<a class="myButton"
			href="<portlet:renderURL>
					<portlet:param name="action" value="viewUserReg"/>
					</portlet:renderURL>">Create An Account
			</a>
	</div>
	<div class="box" id="box">
		<p>Welcome to Ideaclicks.</p>
		<p>Create an account and start sharing your ideas</p>
		<p>Go Ahead ! and experience our game changing platform.</p>
	</div>
</body>
</html>