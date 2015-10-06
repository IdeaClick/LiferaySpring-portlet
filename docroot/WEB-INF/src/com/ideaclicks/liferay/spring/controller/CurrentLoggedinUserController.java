package com.ideaclicks.liferay.spring.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;


@Controller("currentLoggedinUser")
@RequestMapping("VIEW") 
class CurrentLoggedinUserController {
	
	@RenderMapping
	public String home(Map<String, Object> map){
		return "current_loggedin_user";

	}

}
