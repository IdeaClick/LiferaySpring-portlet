package com.ideaclicks.liferay.spring.controller;
import java.io.IOException;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.ideaclicks.liferay.spring.exception.AdminException;
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
						
			if(sessionInfo!=null){
				LOG.info("View Idea Controller Session Info"+sessionInfo);
				renderResponse.setTitle("View Ideas "+" Logged In : "+sessionInfo.getEmail());
				if(sessionInfo.getUsertype().equalsIgnoreCase("User")){	
					System.out.println("1");
					map.put("IdeasList", ideamgmtService.getIdeaList(sessionInfo.getOrgCode(),sessionInfo.getEmail()));
					map.put("categoryList",ListUtils.union(ideamgmtService.getDefaultIdeasCategoryList(), ideamgmtService.getOrganizationIdeasCategoryList(sessionInfo.getOrgCode())));
					return "viewIdeas";
				}
				else if(sessionInfo.getUsertype().equalsIgnoreCase("Admin")){
					System.out.println("2");
					map.put("IdeasList", ideamgmtService.getIdeaListForAdmin(sessionInfo.getOrgCode()));
					map.put("categoryList",ListUtils.union(ideamgmtService.getDefaultIdeasCategoryList(), ideamgmtService.getOrganizationIdeasCategoryList(sessionInfo.getOrgCode())));
					return "admin_viewideas";
				}
			}
			else{
				System.out.println("3");
				LOG.info("Session Info Null");
				return "gotoLoginViewIdeas";
			}
		}catch(Exception e){
			LOG.info("Exception" + e.getStackTrace().toString());
		}
		System.out.println("4");
		return "gotoLoginViewIdeas";
	}
	
	@RenderMapping(params = "action=FilterIdea")
	public String renderOneMethod(RenderRequest request, RenderResponse response, Model model,Map<String,Object> map) throws IOException,
	PortletException,MinervaException {
		try{
			String filterCategory = ParamUtil.getString(request,"filterIdeaCategory");
			PortletSession newSession = request.getPortletSession();
			SessionInfo sessionInfo = (SessionInfo)newSession.getAttribute("sessionInfo",PortletSession.APPLICATION_SCOPE);
			
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
	
	@ActionMapping(params = "action=deleteIdea")
	public void deleteIdeaRequest(@RequestParam("ideasId") String ideasId,ActionRequest actionRequest,ActionResponse actionResponse, Model model)throws IOException,PortletException {
		LOG.info("In DeleteIdea ViewIdeaController");
		System.out.println("In Delete Idea");
		System.out.println("Ideas Id:"+ideasId);
		try {
			ideamgmtService.deleteIdea(ideasId);
		} catch (AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}