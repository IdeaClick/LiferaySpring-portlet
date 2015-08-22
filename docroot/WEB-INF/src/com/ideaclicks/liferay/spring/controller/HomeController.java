package com.ideaclicks.liferay.spring.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

@Controller("homeController")
@RequestMapping("VIEW")
public class HomeController {

	@RenderMapping
	public String home(Map<String, Object> map){
		return "home";

	}
}
