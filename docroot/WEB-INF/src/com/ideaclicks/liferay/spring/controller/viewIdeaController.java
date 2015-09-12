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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.ideaclicks.liferay.spring.service.IdeaManagementService;
import com.ideaclicks.liferay.spring.util.SessionInfo;

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
			SessionInfo sessInfo = (SessionInfo)newSession.getAttribute("sessionInfo",PortletSession.APPLICATION_SCOPE);
			LOG.info("View Idea Controller Session Info"+sessInfo);
			if(sessInfo!=null){
				map.put("IdeasList", ideamgmtService.getIdeaList(sessInfo.getOrgCode()));
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
