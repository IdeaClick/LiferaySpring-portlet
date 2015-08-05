package com.ideaclicks.liferay.spring.controller;

import java.io.IOException;
import java.util.Map;

import javax.portlet.PortletException;
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

import com.ideaclicks.liferay.spring.domain.Ideas;
import com.ideaclicks.liferay.spring.exception.MinervaException;
import com.ideaclicks.liferay.spring.service.IdeaManagementService;
import com.ideaclicks.liferay.spring.util.LiferaySessionUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;

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
	public String submitIdea(Map<String, Object> map)throws MinervaException{
		try {
			map.put("categoryList",ideamgmtService.getIdeasCategoryList());
		} catch (MinervaException me) {
            // redirected to error page
            LOG.debug("check for the exception here" + me.getMessage());
		}
		return "submitIdea";
	}
	
	
	@RenderMapping(params = "action=submitIdea")
	public ModelAndView submitIdeas(RenderRequest renderRequest,RenderResponse renderResponse, Model model,@ModelAttribute("submit_idea") Ideas idea, BindingResult result) throws IOException,
			PortletException, MinervaException {
		boolean value = false;
		try{
						
			Object sessionvalue=  LiferaySessionUtil.getGlobalSessionAttribute("orgCode", renderRequest);
			
			System.out.println("Session value:"+ sessionvalue);
			String currentSessionvalue = sessionvalue.toString();
			System.out.println("currentSessionvalue value:"+ currentSessionvalue);
			idea.setOrgCode(currentSessionvalue);
		
				value=ideamgmtService.SubmitIdea(idea);
				System.out.println("Valllllllllll"+value);
				if(value){
					System.out.println("1");
						return new ModelAndView("viewIdeas","IdeasList",ideamgmtService.getIdeaList(currentSessionvalue));
					}
				else{
						SessionErrors.add(renderRequest, "error");
					}
			}catch (MinervaException me) {
			
					LOG.debug("check for the exception here" + me.getMessage());
			} catch (Exception e) {
			
					LOG.debug("check for the exception here" + e.getMessage());
				}
		return new ModelAndView("viewIdeas");
	}


}
