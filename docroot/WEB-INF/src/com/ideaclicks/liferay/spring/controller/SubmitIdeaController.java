package com.ideaclicks.liferay.spring.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.ideaclicks.liferay.spring.domain.Ideas;
import com.ideaclicks.liferay.spring.exception.MinervaException;
import com.ideaclicks.liferay.spring.service.IdeaManagementService;
import com.ideaclicks.liferay.spring.util.SessionInfo;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

@Controller("submitIdeaController")
@RequestMapping("VIEW")
public class SubmitIdeaController{
	/**
	 * This field holds the logger for this class.
	 */
	private static final Log LOG = LogFactory.getLog(SubmitIdeaController.class);

	@Autowired
	@Qualifier("submitIdeaValidator")
	private Validator validator;

	/*@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}*/
	
	@Resource(name = "validator")
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	@Autowired
	private IdeaManagementService ideamgmtService;

	@RenderMapping
	public String submitIdea(RenderRequest renderRequest, RenderResponse renderResponse, Model model,@ModelAttribute("submit_idea") Ideas idea,Map<String, Object> map) throws IOException,
	PortletException, MinervaException {
		try{
			PortletSession newSession = renderRequest.getPortletSession();
			SessionInfo sessInfo = (SessionInfo)newSession.getAttribute("sessionInfo",PortletSession.APPLICATION_SCOPE);
			LOG.info("Submit Idea Controller Session Info"+sessInfo);
			if(sessInfo!=null){
				map.put("categoryList",ideamgmtService.getIdeasCategoryList());
				return "submitIdea";
			}
			else{
				return "gotoLoginSubmitIdeas";
			}
		}catch(MinervaException e){
			LOG.debug(e.getMessage());
		}catch (Exception e){
			LOG.debug("check for the exception here" + e.getMessage());
		}
		//map.put("categoryList",ideamgmtService.getIdeasCategoryList());
		return "submitIdea";
	}

	@RenderMapping(params = "action=viewSubmitedIdeaPage")
	public ModelAndView renderOneMethod(RenderRequest renderRequest,RenderResponse renderResponse,Map<String, Object> map) throws IOException,
	PortletException,MinervaException {
		return new ModelAndView("submitIdea","categoryList",ideamgmtService.getIdeasCategoryList());
	}

	@ActionMapping(params = "action=submitIdea")
	public void handlePostRequest(ActionRequest actionRequest,ActionResponse actionResponse,@Valid @ModelAttribute("submit_idea") Ideas idea,
			BindingResult result)throws IOException,PortletException,MinervaException {
		
		boolean value = false;
		try{
			validator.validate(idea,result);
			if (result.hasErrors()) {
				LOG.info("validation  failed");
				System.out.println("Validation Failed");
			}
			else{

				PortletSession newSession = actionRequest.getPortletSession();
				SessionInfo sessInfo = (SessionInfo)newSession.getAttribute("sessionInfo",PortletSession.APPLICATION_SCOPE);
				String loginUserOrgCode = sessInfo.getOrgCode();
				String loginuseremail = sessInfo.getEmail();
				idea.setOrgCode(loginUserOrgCode);
				System.out.println("Organization code"+loginUserOrgCode);
				idea.setSubmittedBy(loginuseremail);
				value=ideamgmtService.SubmitIdea(idea);

				if(value){
					ThemeDisplay td  = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
					LOG.info("Home URL"+td.getURLHome());
					SessionMessages.add(actionRequest, "success");
					actionResponse.sendRedirect("http://localhost:8081/group/private/view-idea?p_p_id=ViewIdeas_WAR_IdeaClicksMVPportlet");
					
					//actionResponse.sendRedirect(td.getURLHome()+"/view-ideas?p_p_id=ViewIdeas_WAR_IdeaClicksMVPportlet");
					
				}												
				else{
					// Hide default error message
					SessionErrors.add(actionRequest, "error-key");
					SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
					//display error message
					SessionErrors.add(actionRequest, "error");
					//return new ModelAndView("submitIdea","categoryList",ideamgmtService.getIdeasCategoryList());
				}
			}
		}catch (MinervaException me) {
			LOG.error("Submit Idea Errors"+me.getStackTrace());
			LOG.debug("check for the exception here" + me.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Submit Idea Errors"+e.getStackTrace());
			LOG.debug("check for the exception here" + e.getMessage());
		}
		//return new ModelAndView("submitIdea");
	}
}
