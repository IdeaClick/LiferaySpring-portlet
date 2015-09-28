package com.ideaclicks.liferay.spring.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

@Controller("createUserAccountController")
@RequestMapping("VIEW")
public class CreateUserAccountController {

	@RenderMapping
	public String createUserAccount(Map<String, Object> map){
		return "create_user_account";
	}
}
