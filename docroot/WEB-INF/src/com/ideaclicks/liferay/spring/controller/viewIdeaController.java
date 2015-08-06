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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import com.ideaclicks.liferay.spring.exception.MinervaException;
import com.ideaclicks.liferay.spring.service.IdeaManagementService;
import com.ideaclicks.liferay.spring.util.LiferaySessionUtil;

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
	public String viewIdeas(RenderRequest request, RenderResponse response, Model model,Map<String, Object> map) throws IOException,
				PortletException, MinervaException {
	try{
		Object sessionvalue=  LiferaySessionUtil.getGlobalSessionAttribute("sessionValue", request);
		String currentSessionvalue = sessionvalue.toString();
		map.put("IdeasList", ideamgmtService.getIdeaList(currentSessionvalue));
	}catch(MinervaException e)
	{
		LOG.debug(e.getMessage());
	}catch (Exception e) {
		
		LOG.debug("check for the exception here" + e.getMessage());
	}
		return "viewIdeas";
	}
}
