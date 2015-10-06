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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import com.ideaclicks.liferay.spring.domain.IdeasCategory;
import com.ideaclicks.liferay.spring.exception.AdminException;
import com.ideaclicks.liferay.spring.exception.MinervaException;
import com.ideaclicks.liferay.spring.service.IdeaManagementService;
import com.ideaclicks.liferay.spring.util.SessionInfo;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.util.PortalUtil;

@Controller("categoryController")
@RequestMapping("VIEW")
public class CategoryController {
	/**
	 * This field holds the logger for this class.
	 */
	private static final Log LOG = LogFactory.getLog(CategoryController.class);

	@Autowired
	private IdeaManagementService ideamgmtService;

	/*private Validator validator;

	@Resource(name = "validator")
	public void setValidator(Validator validator) {
		this.validator = validator;
	}*/

	@RenderMapping
	public String render(RenderRequest renderRequest, RenderResponse renderResponse, Model model,@ModelAttribute("add_category")IdeasCategory categoryObj,Map<String, Object> map) throws IOException,
	PortletException, MinervaException {
		try{
			PortletSession newSession = renderRequest.getPortletSession();
			SessionInfo sessionInfo = (SessionInfo)newSession.getAttribute("sessionInfo",PortletSession.APPLICATION_SCOPE);
			if(sessionInfo!=null){
				renderResponse.setTitle("Add Categories "+" Logged In : "+sessionInfo.getEmail());
				if(sessionInfo.getUsertype().equalsIgnoreCase("Admin")){
					map.put("defaultCategoryList",ideamgmtService.getDefaultIdeasCategoryList());
					map.put("organizationCategoryList", ideamgmtService.getOrganizationIdeasCategoryList("Bmc23"));
					return "category";
				}
				else{
					return "this_is_admin_page";
				}
			}
			else{
				return "gotologinadminpage";
			}
		}catch (MinervaException e) {
			e.printStackTrace();
		}catch (Exception e){
			LOG.debug("check for the exception here" + e.getMessage());
		}
		return "gotologinadminpage";
	}

	@ActionMapping(params = "action=addCategory")
	public void addCategoryRequest(ActionRequest actionRequest,ActionResponse actionResponse, Model model,@Valid @ModelAttribute("add_category")IdeasCategory categoryObj,
			BindingResult result)throws IOException,PortletException {
		/*LOG.info("......Before validate IdeasCategory..........");
	validator.validate(categoryObj, result);
		if (result.hasErrors()) {
			LOG.info("validation  failed");
		}
		else{*/
		LOG.info("..........In AddCategory Validation Success.......");
		PortletSession newSession = actionRequest.getPortletSession();
		SessionInfo sessInfo = (SessionInfo)newSession.getAttribute("sessionInfo",PortletSession.APPLICATION_SCOPE);
		//categoryObj.setOrgCode(sessInfo.getOrgCode());
		categoryObj.setOrgCode("Bmc23");		
		try {
			if(ideamgmtService.addCategory(categoryObj)){
				SessionMessages.add(actionRequest, "success");	
			}
			else{
				hideDefaultErrorMessage(actionRequest,actionResponse);
				SessionErrors.add(actionRequest, "error");
			}
		} catch (AdminException ae) {
			hideDefaultErrorMessage(actionRequest,actionResponse);
			SessionErrors.add(actionRequest, "error");
			LOG.error(ae.getStackTrace());
		}catch(Exception e){
			hideDefaultErrorMessage(actionRequest,actionResponse);
			SessionErrors.add(actionRequest, "error");
			LOG.error(e.getStackTrace());
		}
		/*}*/
	}

	@ActionMapping(params = "action=deleteCategory")
	public void deleteCategoryRequest(@RequestParam("categoryId") Integer categoryId,ActionRequest actionRequest,ActionResponse actionResponse, Model model)throws IOException,PortletException {
		LOG.info("In DeleteCategory");
		System.out.println("In DeleteCategory");
		System.out.println("Category Id:"+categoryId);
		try {
			ideamgmtService.deleteCategory(categoryId);
		} catch (AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void  hideDefaultErrorMessage(ActionRequest actionRequest,ActionResponse actionResponse){
		// Hide default error message
		SessionErrors.add(actionRequest, "error-key");
		SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
	}
}
