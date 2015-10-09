<%@page import="javax.portlet.RenderRequest"%>
<%@page import="javax.portlet.PortletSession"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>

<%@page import="com.sun.xml.internal.ws.wsdl.writer.document.ParamType"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%-- <portlet:resourceURL id="like_dislike_counter" var="like_dislike_counter">
	<portlet:param name="action" value="like_dislike_counter" />
</portlet:resourceURL> --%>
<%-- <portlet:renderURL var="userRegURL">
	<portlet:param name="action" value="userReg" />
</portlet:renderURL> --%> 
<%-- 
<portlet:renderURL var="commentURL">
	<portlet:param name="action" value="saveComment"/>						
</portlet:renderURL> --%>
<portlet:resourceURL id="saveComment" var="saveComment" ></portlet:resourceURL>						
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<title>Comment</title>
<style type="text/css">
.view-idea-container {
	width: 90%;
	float: left;
	display: inline-block;
	clear: both;
	height: auto;
	font-weight: bold;
	/*overflow-y: scroll;
	    height: 600px;*/
}

.idea-container {
	border: solid;
	border-width: 2px;
	border-color: #0094BC;
	border-radius: 10px;
	padding: 10px;
}

.idea-container .idea-tile {
	font-size: 24px;
	color: #005777;
	font-weight: bold;
	cursor: pointer;
	text-decoration: none;
}

.idea-container .idea-description {
	font-size: 18px;
	color: #1182FA;
	margin-top: 10px;
	overflow: hidden;
	
}

.idea-container .idea-details-container .category {
	background-color: #CBCBCB;
	color: #FFFFFF;
	padding: 3px;
}

.idea-container .idea-details-container .submit-by {
	color: #0094BC;
}

.categories-container {
	display: inline-block;
	margin-left: 30px;
	width: 200px;
	margin-top: 60px;
	height: auto;
	background-color: #e6e6e6;
	padding-left: 30px;
	padding-bottom: 20px;
	border-radius: 10px;
}

.categories-container .category a {
	cursor: pointer;
	text-decoration: none;
	font-size: 16px;
}

.categories-container .category {
	margin-top: 10px;
}

.search-container {
	padding-left: 40px;
}

.search-container input[type="text"].search-idea {
	width: 75%;
	margin-bottom: 0px;
	border-radius: 0px 10px 0px 10px;
}

.show-idea-container {
	border: solid;
	border-width: 2px;
	border-color: #0094bc;
	border-radius: 10px;
	padding: 10px;
	height: auto;
	font-weight: bold;
}

.show-idea-container .show-idea-description {
	font-size: 18px;
	color: #1182FA;
	margin-top: 10px;
	height: auto;
}

.show-idea-container .show-idea-category {
	background-color: #CBCBCB;
	color: #FFFFFF;
	padding: 3px;
}

.show-idea-container .show-idea-author {
	color: #0094BC;
}

.show-ide-title {
	font-size: 24px;
	color: #005777;
	font-weight: bold;
}
</style>
</head>
<script type="text/javascript">
   $(document).ready(function(){
  	 $('.i-comment').click(function(){
  		 var p1 = $(this).parent().get(0);
  		 $(p1).parent().find('.comment-container').eq(0).show();
  		 $(this).hide();
  	 });
   	$( ".submitComment" ).click(function() 
  			{ 
  				var data = {commentText: $(this).closest('.comment-container').find('.comment-box').eq(0).val()},
  					commentId=$(this).closest('.comment-container').find('.comment-id').eq(0).val();
  				data.commentId=-1;
  				if(commentId!="idea"){
  					data.commentId = commentId;
  				}
  				console.log(data);
  				 $.ajax({ 
  					url: "${saveComment}" , 
  					type: 'POST', 
  					datatype:'json', 
  					data: data, 
  					success: function(data){ 
		  				console.log('success:save comment');	
 					} 
  				}); 
  	}); 
/*    	$( ".submitComment" ).click(function() 
  			{ 
  				var data = {commentText: $(this).prev().val()},
  					commentId=$(this).prev().prev().val();
  				data.commentId=-1;
  				if(commentId!="idea"){
  					data.commentId = commentId;
  				}
  				 $.ajax({ 
  					url: "${saveComment}" , 
  					type: 'POST', 
  					datatype:'json', 
  					data: data, 
  					success: function(data){ 
		  				console.log('success:save comment');	
 					} 
  				}); 
  	}); 
 */
   
   });
</script>
<body>
	<form:form id="like_dislike_comment_form" name="like_dislike_comment"
		modelAttribute="like_dislike_comment" method="post">
		<div class="view-idea-container">
 			<c:forEach items="${IdeasList}" var="Idea">
				<div class="idea-container">
					<div class="idea-tile">${Idea.title}</div><br>
					<div class="idea-description">${Idea.desc}</div><br>
					<div class="idea-details-container">
						<fmt:message key="label.category" />
						<span class="category"> ${Idea.category} </span> <br>
						<fmt:message key="label.submittedby" />
						<span class="submit-by"> ${Idea.submittedBy} </span>
					</div><br>
<%-- 					<input type="image" id="like_btn" name="like_btn" src="<%=renderRequest.getContextPath()%>/images/like.png" alt="Submit" value="${Idea.likes_count}"/>${Idea.likes_count}&nbsp;&nbsp; 
						<input type="image" id="dislike_btn" name="dislike_btn" src="<%=renderRequest.getContextPath()%>/images/dislike.png"
							alt="Submit" />${Idea.dislikes_count}&nbsp;&nbsp;
 --%>					
 					<input type="button" class="i-comment" value="Comment">
					<div class="box comment-container" style="margin-left: auto; margin-right: auto;display:none">
						<input type="hidden" value="idea" class="comment-id"/>
						<textarea class="comment-box" name="<portlet:namespace />commentsText"
						title="Submit Your comment"
						style="width: 95%; height: 50px;"></textarea>
					
						<button name="submit" value="submit" style="width:100px" class="submitComment">Submit</button>	
					</div>
				</div>

 				<c:forEach items="${comments}" var="comment">
	            	<template:nodeTree node="${comment}"/>
	            </c:forEach> 
	            				
			</c:forEach>
		</div>
	</form:form>
</body>
</html>