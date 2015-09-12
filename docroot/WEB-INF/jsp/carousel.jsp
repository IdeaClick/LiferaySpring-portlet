<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<portlet:defineObjects />
<!DOCTYPE html PUBLIC>
<html>
<head>
<style type="text/css">
#myCarousel .content_box { 
	padding: 2%; 
}

#myCarousel .aui-carousel-item {
	height: auto;
	position: relative;
	display: none;
	border: 3px solid #ccc;
}

#myCarousel .aui-carousel-item-active { 
	display: block; 
}

#myCarousel .aui-carousel-item img {
	width: 100%;
	height: auto;
	margin-bottom: -5px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="http://cdn.alloyui.com/3.0.1/aui/aui-min.js"></script>
<link href="http://cdn.alloyui.com/3.0.1/aui-css/css/bootstrap.min.css" rel="stylesheet"></link></head>
<body>
<div id="myCarousel">
  <div class="image-viewer-base-image"><img src="<%=renderRequest.getContextPath()%>/images/header.jpg" style="height=100%; width=100%"></div>
  <div class="image-viewer-base-image"><img src="<%=renderRequest.getContextPath()%>/images/yourgreatidea.jpg " style="height=100%; width=100%"></div>
  <div class="image-viewer-base-image"><img src="<%=renderRequest.getContextPath()%>/images/share1.png" style="height=100%; width=100%"></div>
  <div class="image-viewer-base-image"><img src="<%=renderRequest.getContextPath()%>/images/share.jpg" style="height=100%; width=100%"></div>
</div>
	

<script>

YUI().use(
		  'aui-carousel',
		  function(Y) {
		    new Y.Carousel(
		      {
		    	  activeIndex: 'rand',
		          contentBox: '#myCarousel',
		          intervalTime: 7,
		          height:250
		          //width: 700 /* hide the width for parent of #myCarousel container */
		      }
		    ).render();
		  }
		);
	</script>
</body>
</html>