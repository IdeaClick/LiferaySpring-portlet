<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<portlet:defineObjects/>

<!DOCTYPE html>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	  <script src="${pageContext.request.contextPath}/js/aui-min.js"></script>
      <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"></link>
<title>View Ideas</title>

<style type="text/css">
	.view-idea-container {		  
		width: 70%;
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
	.idea-container a.idea-tile{
		font-size: 24px;
		color: #005777;
    	font-weight: bold;
    	cursor: pointer;
    	text-decoration:none;
    }
	.idea-container .idea-description {
		font-size: 18px;
		color: #0094BC;
		margin-top: 10px;
	    height: 60px;
	    overflow: hidden;
	    max-width: 700px;
	}
	.idea-container .idea-details-container .category{
		background-color: #CBCBCB;
		color: #FFFFFF;
		padding: 3px;
	}
	.idea-container .idea-details-container .submit-by{
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
	.categories-container .category a{	
		cursor: pointer;
    	text-decoration:none;
    	font-size: 16px;	
	}	
	.categories-container .category{
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
	    color: #0094bc;
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
<body>

<div>
<form:form id="viewIdea" name="viewIdea" modelAttribute="viewIdea" method="post" action="">	

    
    <div class="view-idea-container">
    	<!-- <div class="search-container">
	    	Keywords : <input type="text" name="searchIdeas" class="search-idea"/> 
	    	<button class="btn btn-info">Search</button>
    	</div>-->
    	<h2>Ideas</h2>
    	<c:forEach items="${IdeasList}" var="Idea">
    					
    			
    	<div class="idea-container">
    		<a class="idea-tile">
    			${Idea.title}
    		</a>
    		<br>
    		<div class="idea-description">
    			${Idea.desc}
    		</div>
    		<br>
    		<div class="idea-details-container">
    			Category : 
    			<span class="category">
    				${Idea.category}
    			</span>
    			<br>
    			Submitted By:
    			<span class="submit-by">
    				${Idea.author}
    			</span>

    		</div>
    	</div>
    	<br>
    	</c:forEach>   	

    	<!-- <div class="idea-container">
    		<a class="idea-tile">
    			Liferay Framework
    		</a>
    		<div class="idea-description">
    		Liferay, Inc., is a professional open-source company that provides free documentation and paid professional service to users of its software. Mainly focused on enterprise portal technology, the company has its headquarters in Diamond Bar, California, United States.
			Liferay Portal was created in 2000 by chief software architect Brian Chan to provide an enterprise portal solution for non-profit organizations.[7] 
			In 2004, the company was incorporated under the name Liferay, Inc. and formalized its Germany subsidiary Liferay GmbH. 
			In 2007, the company opened new Asian headquarters in Dalian, China, and the Spanish subsidiary Liferay SL. 
			In March 2009, the company opened a new office in Bangalore, India. To date there are 18 offices in 15 countries worldwide with a partner network of 110+ in 40 countries.   		
    		</div>
    		<br>
    		<div class="idea-details-container">
    			Category : 
    			<span class="category">
    				Web
    			</span>
    			<br>
    			Submitted By:
    			<span class="submit-by">
    				CCC
    			</span>
    		</div>
    	</div>
    	<br>

    	<div class="idea-container">
    		<a class="idea-tile">
    			Jquery Library
    		</a>
    		<div class="idea-description">
    		The jQuery library is a single JavaScript file containing all of its common DOM, event, effects, and Ajax functions. It can be included within a web page by linking to a local copy or to one of the many copies available from public servers. jQuery has a CDN hosted by MaxCDN[13] (moved from MediaTemple[14] and, before that, Amazon[15]). Google[16] and Microsoft[17] host it as well.    		</div>
    		<br>
    		<div class="idea-details-container">
    			Category : 
    			<span class="category">
    				Web
    			</span>
    			<br>
    			Submitted By:
    			<span class="submit-by">
    				AAA
    			</span>
    		</div>
    	</div>
    	<br>

    	<div class="idea-container">
    		<a class="idea-tile">
    			Enabling the community
    		</a>
    		<div class="idea-description">
    		The goal for Plasma Mobile is to give the user full use of the device. It is designed as an inclusive system, intended to support all kinds of apps. Native apps are developed using Qt; it will also support apps written in GTK, Android apps, Ubuntu apps, and many others, if the license allows and the app can be made to work at a technical level.
			Plasma Mobile's development process welcomes contributions at all levels. If you want to get your hands dirty with a cool app, if you want to provide a system functionality such as a mobile hotspot, if you want to improve power management at the kernel level, if you want to help with the design, Plasma Mobile welcomes your contributions.
			If you want to take part in the creation of Plasma Mobile, get in touch with us!   
			The goal for Plasma Mobile is to give the user full use of the device. It is designed as an inclusive system, intended to support all kinds of apps. Native apps are developed using Qt; it will also support apps written in GTK, Android apps, Ubuntu apps, and many others, if the license allows and the app can be made to work at a technical level.
			Plasma Mobile's development process welcomes contributions at all levels. If you want to get your hands dirty with a cool app, if you want to provide a system functionality such as a mobile hotspot, if you want to improve power management at the kernel level, if you want to help with the design, Plasma Mobile welcomes your contributions.
			If you want to take part in the creation of Plasma Mobile, get in touch with us!
			</div>
    		<br>
    		<div class="idea-details-container">
    			Category : 
    			<span class="category">
    				Mobile
    			</span>
    			<br>
    			Submitted By:
    			<span class="submit-by">
    				BBB
    			</span>
    		</div>
    	</div>-->
    </div>
	<div class="categories-container">
		<h4>Categories</h4>
		<div  class="category">
			<a>
				Mobile
			</a>
		</div>
		<div  class="category">
			<a>
				Agriculture
			</a>
		</div>
		<div  class="category">
			<a>
				Television
			</a>
		</div>
		<div  class="category">
			<a>
				Property
			</a>
		</div>
	</div>	
</form:form>
</div><!-- end of main -->  
<aui:script  use="event, node">

AUI().ready(
		  'aui-aria',
		  'aui-dialog',
		  'aui-overlay-manager',
		  'dd-constrain',
		  function(A) {
		    A.all('.idea-tile').each(function() {
		      this.on('click', function(event){
		    	var login_popup= Liferay.Util.Window.getWindow(
		                {
		                   dialog: {
		                        centered: true,
		                       constrain2view: true,
		                        modal: true,
		                        resizable: false,
		                        width: 550,
		                        height: 450,
		                        bodyContent:"<div class='show-idea-container'><div class='show-idea-description'>"+this.get('parentNode').one(".idea-description").html()+"</div><br>"+
		                        			"Category : <span class='show-idea-category'>"+this.get('parentNode').one(".idea-details-container .category").html()+"</span><br>"+
		                        			"Submitted By : <span class='show-idea-author'>"+this.get('parentNode').one(".idea-details-container .submit-by").html()+"</span></div>"
		                    }
		                }).render();
		             login_popup.show();
		             console.log(this.html());
		             login_popup.titleNode.html("<div class='show-ide-title'>"+this.text()+"</div>");
		             //login_popup.setStyle('background-color','yellow');
		      });
		    });
		  });
 
 </aui:script>

</body>

</html>
