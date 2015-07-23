package com.ideaclicks.liferay.spring.base;

import java.beans.PropertyEditorSupport;

/**
 * Property editor for coverting from/to Boolean object.
 * 
 * @author asarin
 */
public class BooleanPropertyEditor extends PropertyEditorSupport {
	
	/**
	 * Gets the boolean value in the command object as a String.
	 */
	public String getAsText() {
		String text = null;
		boolean value = (Boolean) getValue();
		if(value) {
			text = "Y";
		} else {
			text = "N";
		}
		return text;
	}
	
	/**
	 * Sets the boolean value in the command object as a String.
	 */
	public void setAsText(String text)  throws IllegalArgumentException {
		if(text != null && text.equals("Y")) {
			setValue(true);
		} else {
			setValue(false);
		}
	}
}
