package com.ideaclicks.liferay.spring.controller;
import java.io.IOException;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

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
	public String login(RenderRequest request, RenderResponse response, Model model,Map<String, Object> map) throws IOException,
	PortletException {
		try{
			PortletSession newSession = request.getPortletSession();
			SessionInfo sessionInfo = (SessionInfo)newSession.getAttribute("sessionInfo",PortletSession.APPLICATION_SCOPE);
			LOG.info("View Idea Controller Session Info"+sessionInfo);
			
			if(sessionInfo!=null){
				System.out.println("User Type:"+sessionInfo.getUsertype()+"Orgcode:"+sessionInfo.getOrgCode());
				if(sessionInfo.getUsertype().equalsIgnoreCase("User")){
					map.put("IdeasList", ideamgmtService.getIdeaList(sessionInfo.getOrgCode(),sessionInfo.getEmail()));
					map.put("categoryList",ideamgmtService.getIdeasCategoryList());
					return "viewIdeas";
				}
				else{
					map.put("IdeasList", ideamgmtService.getIdeaListForAdmin(sessionInfo.getOrgCode()));
					map.put("categoryList",ideamgmtService.getIdeasCategoryList());
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
		return "viewIdeas";
	}
	
	@RenderMapping(params = "action=FilterIdea")
	public String renderOneMethod(RenderRequest request, RenderResponse response, Model model,Map<String,Object> map) throws IOException,
	PortletException,MinervaException {
		try{
			String filterCategory = ParamUtil.getString(request,"filterIdeaCategory");
			System.out.println("Fiter Idea Category"+filterCategory);
			PortletSession newSession = request.getPortletSession();
			SessionInfo sessionInfo = (SessionInfo)newSession.getAttribute("sessionInfo",PortletSession.APPLICATION_SCOPE);
			LOG.info("View Idea Controller Session Info"+sessionInfo);
			if(sessionInfo!=null){
				map.put("IdeasList", ideamgmtService.getIdeaFilterList(sessionInfo.getOrgCode(),sessionInfo.getEmail(),filterCategory));
				map.put("categoryList",ideamgmtService.getIdeasCategoryList());
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
}
