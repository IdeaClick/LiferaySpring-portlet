package com.ideaclicks.liferay.spring.base;

/**
 * SearchableSetElement interface is implemented by a class which is an element
 * in a Set. Useful only in case the element needs to be searched in a Set 
 * based on key.
 * 
 * @author asarin
 */
public interface SearchableSetElement {
	/**
	 * Return the key of the element.
	 * 
	 * @return
	 */
	public Long getElementKey();
}
