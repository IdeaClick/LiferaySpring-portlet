package com.ideaclicks.liferay.spring.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.ideaclicks.liferay.spring.domain.UserRegistration;
import java.util.regex.Matcher;  
import java.util.regex.Pattern;


public class UserRegistrationValidator implements Validator {

	private Pattern pattern;  
	private Matcher matcher; 

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"  
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";  
	String ID_PATTERN = "[0-9]+";  
	String STRING_PATTERN = "[a-zA-Z]+";  
	String MOBILE_PATTERN = "[0-9]{10}";  //set contact number length [0-9]{10} 

	public boolean supports(Class<?> paramClass) {
		return UserRegistration.class.equals(paramClass);
	}

	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orgCode","UserRegistration.orgCode" );
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName","UserRegistration.userName" );
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "UserRegistration.email");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contact","UserRegistration.contact" );
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pswd", "UserRegistration.pswd");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cpswd", "UserRegistration.cpswd");

		UserRegistration data = (UserRegistration) obj;
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
		}
*/	}
}