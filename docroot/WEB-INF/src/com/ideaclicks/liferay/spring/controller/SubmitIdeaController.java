package com.ideaclicks.liferay.spring.controller;

import java.io.IOException;
import java.util.Map;
import com.ideaclicks.liferay.spring.exception.MinervaException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.ideaclicks.liferay.spring.domain.Ideas;
import com.ideaclicks.liferay.spring.service.IdeaManagementService;

@Controller("submitIdeaController")
@RequestMapping("VIEW")
public class SubmitIdeaController {
	/**
     * This field holds the logger for this class.
     */
    private static final Log LOG = LogFactory.getLog(SubmitIdeaController.class);
    
	@Autowired
	private IdeaManagementService ideamgmtService;
	
	@RenderMapping
	public String submitIdea(Map<String, Object> map) {
		map.put("submit_idea", new Ideas());
		return "submit_idea";
	}
	
	
	@ActionMapping(params = "action=submitIdea")
	public void submitIdeas(ActionRequest actionRequest,ActionResponse actionResponse, Model model,@ModelAttribute("submit_idea") Ideas idea, BindingResult result) throws IOException,
			PortletException, MinervaException {
		
		
		System.out.println("Title:"+idea.getTitle());
		System.out.println("Desc:"+idea.getDesc());
		System.out.println("Category:"+idea.getCategory());
		try{
				boolean b=ideamgmtService.SubmitIdea(idea);
				if(b){
						System.out.println("Idea Submitted");
					}
				else{
						System.out.println("Idea not Submitted");
					}
			}catch (MinervaException me) {
				// redirected to error page
					LOG.debug("check for the exception here" + me.getMessage());
			} catch (Exception e) {
				// redirected to error page
					LOG.debug("check for the exception here" + e.getMessage());
				}
		//return "login";
	}

}
