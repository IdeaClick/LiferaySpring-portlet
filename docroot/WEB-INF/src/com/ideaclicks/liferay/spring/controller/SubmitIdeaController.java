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

import org.apache.commons.collections.ListUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
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

	@Resource(name = "validator")
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	@Autowired
	private IdeaManagementService ideamgmtService;

	@RenderMapping
	public ModelAndView submitIdea(RenderRequest renderRequest, RenderResponse renderResponse, Model model,@ModelAttribute("submit_idea") Ideas idea,Map<String, Object> map) throws IOException,
	PortletException, MinervaException {
		try{
			PortletSession newSession = renderRequest.getPortletSession();
			SessionInfo sessInfo = (SessionInfo)newSession.getAttribute("sessionInfo",PortletSession.APPLICATION_SCOPE);	
			if(sessInfo!=null){
				System.out.println("1");
				LOG.info("Submit Idea Controller Session Info"+sessInfo);
				renderResponse.setTitle("Submit Idea "+" Logged In : "+sessInfo.getEmail());
				return new ModelAndView("submitIdea","categoryList",ListUtils.union(ideamgmtService.getDefaultIdeasCategoryList(), ideamgmtService.getOrganizationIdeasCategoryList(sessInfo.getOrgCode())));
			}
			else{
				System.out.println("2");
				return new ModelAndView("gotoLoginSubmitIdeas");
			}
		}catch(MinervaException ee){
			LOG.debug(ee.getMessage());
		}catch (Exception e){
			LOG.debug("check for the exception here" + e.getMessage());
		}
		System.out.println("3");
		return new ModelAndView("gotoLoginSubmitIdeas");
	}

	@RenderMapping(params = "action=viewSubmitedIdeaPage")
	public ModelAndView renderOneMethod(RenderRequest renderRequest,RenderResponse renderResponse,Model model,@ModelAttribute("submit_idea") Ideas idea,Map<String, Object> map) throws IOException,
	PortletException,MinervaException {
		return new ModelAndView("submitIdea","categoryList",ideamgmtService.getDefaultIdeasCategoryList());
	}

	@ActionMapping(params = "action=submitIdea")
	public void handlePostRequest(ActionRequest actionRequest,ActionResponse actionResponse,@Valid @ModelAttribute("submit_idea") Ideas idea,
			BindingResult result)throws IOException,PortletException,MinervaException {

		boolean value = false;
		try{
			validator.validate(idea,result);
			if (result.hasErrors()) {
				LOG.info("validation  failed");
			}
			else{
				PortletSession newSession = actionRequest.getPortletSession();
				SessionInfo sessInfo = (SessionInfo)newSession.getAttribute("sessionInfo",PortletSession.APPLICATION_SCOPE);
				String loginUserOrgCode = sessInfo.getOrgCode();
				String loginuseremail = sessInfo.getEmail();
				idea.setOrgCode(loginUserOrgCode);
				System.out.println("Organization code"+loginUserOrgCode);
				idea.setSubmittedBy(loginuseremail);
				if(idea.getIdeaStatus()==null){
					idea.setIdeaStatus("public");
				}
				value=ideamgmtService.SubmitIdea(idea);

				if(value){
					ThemeDisplay td  = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
					LOG.info("Home URL"+td.getURLHome());
					SessionMessages.add(actionRequest, "success");
					actionResponse.sendRedirect("http://localhost:8080/web/liferay/view-idea?p_p_id=ViewIdeas_WAR_IdeaClicksMVPportlet");
					//actionResponse.sendRedirect(td.getURLHome()+"/view-ideas?p_p_id=ViewIdeas_WAR_IdeaClicksMVPportlet");
				}												
				else{
					SessionErrors.add(actionRequest, "error-key");
					SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
					SessionErrors.add(actionRequest, "error");
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
	}
}
