package com.ideaclicks.liferay.spring.controller;

import java.io.IOException;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.ideaclicks.liferay.spring.domain.CommentPojo;
import com.ideaclicks.liferay.spring.domain.Ideas;
import com.ideaclicks.liferay.spring.exception.AdminException;
import com.ideaclicks.liferay.spring.exception.MinervaException;
import com.ideaclicks.liferay.spring.service.IdeaManagementService;
import com.ideaclicks.liferay.spring.util.SessionInfo;
import com.liferay.portal.kernel.util.ParamUtil;

@Controller("commentController")
@RequestMapping("VIEW")
public class CommentController{

	/**
	 * This field holds the logger for this class.
	 */   
	private static final Log LOG = LogFactory.getLog(CommentController.class);

	@Autowired
	private IdeaManagementService ideamgmtService;

	String ideasId= ""; 

	@RenderMapping(params = "action=commentsOnIdea")
	public String renderOneMetho(RenderRequest request, RenderResponse response,Map<String, Object> map, Model model, @ModelAttribute("idea_comment_form") Ideas idea, BindingResult result) throws IOException,
	PortletException,MinervaException {
		ideasId = ParamUtil.getString(request, "Ideas_id");
		LOG.info("Title Vvalue"+ideasId);
		LOG.info("IdeasList"+ideamgmtService.getSingleIdea(ideasId));
		try {
			model.addAttribute("like_dislike_comment", new CommentPojo());
			map.put("comments", ideamgmtService.getComment(ideasId));
			map.put("IdeasList", ideamgmtService.getSingleIdea(ideasId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "comment";
	}

	/*@ResourceMapping(value ="like_dislike_counter")
	public @ResponseBody void likeDislikeCounter(ResourceRequest request,ResourceResponse response,Model model,@ModelAttribute("like_dislike_comment") Comments comment)throws IOException,PortletException{

	int like_count = ParamUtil.getInteger(request, "like_count");
			LOG.info("Hiiiiiiiiiiii"+like_count);
			like_count = like_count + 1 ;

          try{
        	  int likeCount = ParamUtil.getInteger(request, "like_count");
  			LOG.info("Hii Amol!!"+likeCount);	
  			likeCount = likeCount+1;
  			response.getWriter().println(likeCount);

        	 response.setCharacterEncoding("UTF-8"); 
        	  LOG.info("Like Count"+like_count);
      		response.getWriter().write(like_count); 

          }catch (Exception e) {
        	  e.printStackTrace();
          }
	}*/

	@ResourceMapping(value="saveComment") 
	public void saveComment(ResourceRequest request, ResourceResponse response) throws IOException { 
		LOG.info("inside find state");
		String commentText = ParamUtil.getString(request, "commentText"); 
		LOG.info("comment text="+commentText);
		String commentId = ParamUtil.getString(request, "commentId"); 
		LOG.info("comment id="+commentId);
		LOG.info("idea id="+ideasId);
		PortletSession newSession = request.getPortletSession();
		SessionInfo sessInfo = (SessionInfo)newSession.getAttribute("sessionInfo",PortletSession.APPLICATION_SCOPE);

		String loginuseremail = sessInfo.getEmail();
		CommentPojo comment = new CommentPojo();
		comment.setCommentsText(commentText);
		comment.setSubmittedBy(loginuseremail);
		if(!commentId.equals("-1")) {
			comment.setParentCommentsId(commentId);			
		} else {
			comment.setParentIdeaId(ideasId);
		}
		LOG.info(comment);
		try {
			ideamgmtService.saveComment(comment);
		} catch (AdminException e) {
			LOG.info("save comment exception="+e);
			e.printStackTrace();
		}
	}
}
