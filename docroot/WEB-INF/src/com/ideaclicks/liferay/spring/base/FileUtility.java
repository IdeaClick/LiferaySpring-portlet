package com.ideaclicks.liferay.spring.base;

import java.io.IOException;
import java.io.InputStream;

/**
 * Provides utility methods for handling files.
 * 
 * @author asarin
 */
public class FileUtility {
	/**
	 * Checks that the file represented by the InputStream is not more than a particular size.
	 * 
	 * @param inStream inout stream
	 * @param maxSize the maximum size in bytes
	 * @return true if the size of the file is more than maxSize
	 * 				false if the size of the file is less than or equal to maxSize
	 * @throws IOException
	 */
	public static boolean hasReachedMaxSize(InputStream inStream, long maxSize) throws IOException {
		boolean hasReachedMaxSize = false;
		if(inStream.available() > maxSize) {
			hasReachedMaxSize = true;
		}
		return hasReachedMaxSize;
	}
}