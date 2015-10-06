package com.ideaclicks.liferay.spring.controller;
import java.io.IOException;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.ideaclicks.liferay.spring.domain.Ideas;
import com.ideaclicks.liferay.spring.domain.OrganizationRegistration;
import com.ideaclicks.liferay.spring.exception.MinervaException;
import com.ideaclicks.liferay.spring.service.IdeaManagementService;
import com.ideaclicks.liferay.spring.util.SessionInfo;
import com.liferay.portal.kernel.util.ParamUtil;

@Controller("viewideaController")
@RequestMapping("VIEW")
public class viewIdeaController {
	/**
	 * This field holds the logger for this class.
	 */
	private static final Log LOG = LogFactory.getLog(viewIdeaController.class);

	@Autowired
	private IdeaManagementService ideamgmtService;

	@RenderMapping 
	public String viewIdeas(RenderRequest renderRequest, RenderResponse renderResponse, Model model,Map<String, Object> map) throws IOException,
	PortletException {
		try{
			PortletSession newSession = renderRequest.getPortletSession();
			SessionInfo sessionInfo = (SessionInfo)newSession.getAttribute("sessionInfo",PortletSession.APPLICATION_SCOPE);
			LOG.info("View Idea Controller Session Info"+sessionInfo);
			
			if(sessionInfo!=null){
				System.out.println("User Type:"+sessionInfo.getUsertype()+"Orgcode:"+sessionInfo.getOrgCode());
				renderResponse.setTitle("View Ideas "+" Logged In : "+sessionInfo.getEmail());
				if(sessionInfo.getUsertype().equalsIgnoreCase("User")){					
					map.put("IdeasList", ideamgmtService.getIdeaList(sessionInfo.getOrgCode(),sessionInfo.getEmail()));
					map.put("categoryList",ListUtils.union(ideamgmtService.getDefaultIdeasCategoryList(), ideamgmtService.getOrganizationIdeasCategoryList(sessionInfo.getOrgCode())));
					return "viewIdeas";
				}
				else if(sessionInfo.getUsertype().equalsIgnoreCase("Admin")){
					System.out.println("In Admin");
					map.put("IdeasList", ideamgmtService.getIdeaListForAdmin(sessionInfo.getOrgCode()));
					map.put("categoryList",ListUtils.union(ideamgmtService.getDefaultIdeasCategoryList(), ideamgmtService.getOrganizationIdeasCategoryList(sessionInfo.getOrgCode())));
					return "admin_viewideas";
				}
			}
			else{
				return "gotoLoginViewIdeas";
			}
		}catch(Exception e){
			LOG.error("Exception " + e.getMessage());
			LOG.info("Exception" + e.getStackTrace().toString());
		}
		return "gotoLoginViewIdeas";
	}
	
	@RenderMapping(params = "action=FilterIdea")
	public String renderOneMethod(RenderRequest request, RenderResponse response, Model model,Map<String,Object> map) throws IOException,
	PortletException,MinervaException {
		try{
			String filterCategory = ParamUtil.getString(request,"filterIdeaCategory");
			System.out.println("Fiter Idea Category"+filterCategory);
			PortletSession newSession = request.getPortletSession();
			SessionInfo sessionInfo = (SessionInfo)newSession.getAttribute("sessionInfo",PortletSession.APPLICATION_SCOPE);
			
			System.out.println("View Idea Controller Session Info"+sessionInfo);
			if(sessionInfo!=null){
			
				map.put("IdeasList", ideamgmtService.getIdeaFilterList(sessionInfo.getOrgCode(),sessionInfo.getEmail(),filterCategory));
				map.put("categoryList",ListUtils.union(ideamgmtService.getDefaultIdeasCategoryList(), ideamgmtService.getOrganizationIdeasCategoryList(sessionInfo.getOrgCode())));
				return "viewIdeas";
			}
			else{
				return "gotoLoginViewIdeas";
			}
		}catch(Exception e){
			LOG.error("Exception " + e.getMessage());
			LOG.info("Exception" + e.getStackTrace().toString());
		}
		return "viewIdeas";
	}

	/*@ResourceMapping(value ="like_dislike_counter")
	public @ResponseBody void saveDomment(ResourceRequest request,ResourceResponse response,Model model,@ModelAttribute("like_dislike_comment") Ideas comment,BindingResult result)throws IOException,PortletException{
	System.out.println("Hii Neha");	
			int like_count = ParamUtil.getInteger(request, "like_count");
			System.out.println("Hiiiiiiiiiiii"+like_count);
			like_count = like_count + 1 ;
		 
          try{
        	  int likeCount = ParamUtil.getInteger(request, "like_count");
  			System.out.println("Hii Amol!!"+likeCount);	
  			likeCount = likeCount+1;
  			response.getWriter().println(likeCount);
  			
        	 response.setCharacterEncoding("UTF-8"); 
        	  System.out.println("Like Count"+like_count);
      		response.getWriter().write(like_count); 
         
          }catch (Exception e) {
        	  e.printStackTrace();
          }	           
	}*/
}