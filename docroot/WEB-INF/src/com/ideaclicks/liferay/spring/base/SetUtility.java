package com.ideaclicks.liferay.spring.base;

import java.util.Iterator;
import java.util.Set;

public class SetUtility {
	/**
	 * Return the element of a Set ( whose elements implement SearchableSetElement )
	 * based on the key value passed.
	 * 
	 * @param data the Set that contains SearchableSetElement
	 * @param key the key based on which the SearchableSetElement is to be returned
	 * @return the element in the Set that matches the key
	 */
	@SuppressWarnings("unchecked")
	public static SearchableSetElement getByKey(Set data, Long key) {
		Iterator<SearchableSetElement> iterator = data.iterator();
		SearchableSetElement returnObj = null;
		while(iterator.hasNext()) {
			SearchableSetElement searchableSetElement = iterator.next();
			if(searchableSetElement.getElementKey().equals(key)) {
				returnObj = searchableSetElement;
				break;
			}
		}
		return returnObj;
	}
	
	/**
	 * Removes the element of a Set ( whose elements implement SearchableSetElement )
	 * based on the key value passed.
	 *
	 * @param data the Set that contains SearchableSetElement
	 * @param key the key based on which the SearchableSetElement is to be removed
	 * @return true if the removal is successful, else false
	 */
	@SuppressWarnings("unchecked")
	public static boolean removeElement(Set data, Long key) {
		Iterator<SearchableSetElement> iterator = data.iterator();
		boolean removed = false;
		while(iterator.hasNext()) {
			SearchableSetElement searchableSetElement = iterator.next();
			if(searchableSetElement.getElementKey().equals(key)) {
				data.remove(searchableSetElement);
				removed = true;
			}
		}
		return removed;
	}
}
