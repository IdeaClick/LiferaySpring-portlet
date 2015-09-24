package com.ideaclicks.liferay.spring.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ideaclicks.liferay.spring.domain.Ideas;


public class SubmitIdeaValidator implements Validator {


	public boolean supports(Class<?> paramClass) {
		return Ideas.class.equals(paramClass);
	}

	public void validate(Object obj, Errors errors) {
		System.out.println("In Submit Idea Validator");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title","Ideas.title");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "desc","Ideas.desc");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "Ideas.category");
		
	}
}