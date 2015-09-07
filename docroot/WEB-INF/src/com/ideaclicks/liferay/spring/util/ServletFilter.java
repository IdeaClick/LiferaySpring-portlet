package com.ideaclicks.liferay.spring.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.filter.ActionFilter;
import javax.portlet.filter.RenderFilter;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

public class ServletFilter implements ActionFilter,RenderFilter {

	GlobalConstants constant = new GlobalConstants();

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("#####Destroy#####");
	}

	@Override
	public void init(javax.portlet.filter.FilterConfig arg0)
			throws PortletException {
		// TODO Auto-generated method stub
		System.out.println("###init####");

	}

	@SuppressWarnings("deprecation")
	@Override
	public void doFilter(RenderRequest request, RenderResponse response,
			javax.portlet.filter.FilterChain chain) throws IOException,ArrayIndexOutOfBoundsException,
			PortletException {
		
		// TODO Auto-generated method stub
		try {
			boolean flag = true;
			System.out.println("#####Render####");
			ThemeDisplay td  = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			String currentURL = td.getURLCurrent();
		
			System.out.println("1"+currentURL); 
			System.out.println("Length="+currentURL.length());
			
			for(int i=0;i<GlobalConstants.special_char.length;i++){
				if(currentURL.indexOf(GlobalConstants.special_char[i])>0){
					System.out.println("found");
					flag = false;
				}
			}
			if(flag){
				chain.doFilter(request, response);
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void doFilter(ActionRequest request, ActionResponse response,
			javax.portlet.filter.FilterChain chain) throws IOException,
			PortletException {
		// TODO Auto-generated method stub
		System.out.println("#######Action####");
		chain.doFilter(request, response);
	}
}
