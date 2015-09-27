package com.ideaclicks.liferay.spring.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.ideaclicks.liferay.spring.domain.OrganizationRegistration;
import java.util.regex.Matcher;  
import java.util.regex.Pattern;


public class OrganizationRegValidator implements Validator {

	private Pattern pattern;  
	private Matcher matcher; 

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"  
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";  
	String ID_PATTERN = "[0-9]+";  
	String STRING_PATTERN = "[a-zA-Z]+";  
	String MOBILE_PATTERN = "[0-9]{10}";  //set contact number length [0-9]{10} 

	public boolean supports(Class<?> paramClass) {
		return OrganizationRegistration.class.equals(paramClass);
	}

	public void validate(Object obj, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orgName","OrganizationRegistration.orgName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orgCode","OrganizationRegistration.orgCode");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orgType", "OrganizationRegistration.orgType");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email","OrganizationRegistration.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pswd", "OrganizationRegistration.pswd");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cpswd", "OrganizationRegistration.cpswd");

		OrganizationRegistration data = (OrganizationRegistration) obj;
		if (!data.getPswd().equals(data.getCpswd())) {
			errors.rejectValue("cpswd", "valid.passwordConfDiff");
		}
		if (!(data.getEmail() != null && data.getEmail().isEmpty())) {  
			pattern = Pattern.compile(EMAIL_PATTERN);  
			matcher = pattern.matcher(data.getEmail());  
			if (!matcher.matches()) {  
				errors.rejectValue("email", "UserRegistration.emailPattern");  
			}  
		} 
		
		/*if(!(data.getContact() != null && data.getContact().isEmpty())){
			pattern = Pattern.compile(MOBILE_PATTERN);
			matcher = pattern.matcher(data.getContact());
			if(!matcher.matches()){
				errors.rejectValue("contact", "UserRegistration.contactPattern");
			}
		}*/
	}
}