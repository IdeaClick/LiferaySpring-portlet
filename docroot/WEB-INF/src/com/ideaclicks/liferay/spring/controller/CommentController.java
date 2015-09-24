package com.ideaclicks.liferay.spring.controller;

import java.io.IOException;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import com.liferay.portal.kernel.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.ideaclicks.liferay.spring.domain.Ideas;
import com.ideaclicks.liferay.spring.exception.MinervaException;
import com.ideaclicks.liferay.spring.service.IdeaManagementService;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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
		System.out.println("Title Vvalue"+ideasId);
		System.out.println("IdeasList"+ideamgmtService.getSingleIdea(ideasId));
		try {
			map.put("IdeasList", ideamgmtService.getSingleIdea(ideasId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "comment";
	}

	@ResourceMapping(value ="like_dislike_counter")
	public @ResponseBody void likeDislikeCounter(ResourceRequest request,ResourceResponse response,Model model)throws IOException,PortletException{
		
		
		
		
		/*	int like_count = ParamUtil.getInteger(request, "like_count");
			System.out.println("Hiiiiiiiiiiii"+like_count);
			like_count = like_count + 1 ;
		 */
          try{
        	  int likeCount = ParamUtil.getInteger(request, "like_count");
  			System.out.println("Hii Amol!!"+likeCount);	
  			likeCount = likeCount+1;
  			response.getWriter().println(likeCount);
  			
        	/*  response.setCharacterEncoding("UTF-8"); 
        	  System.out.println("Like Count"+like_count);
      		response.getWriter().write(like_count); 
         */
          }catch (Exception e) {
        	  e.printStackTrace();
          }
         
		           
	}

}
