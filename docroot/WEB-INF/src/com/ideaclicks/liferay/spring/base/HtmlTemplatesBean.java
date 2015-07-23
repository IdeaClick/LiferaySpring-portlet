package com.ideaclicks.liferay.spring.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

/**
 * HtmlTemplatesBean provides AJAX request handler with the HTML templates defined in the
 * htmlTemplates.properties file.
 * 
 * @author asarin
 */
public class HtmlTemplatesBean {
	private static Logger logger = Logger.getLogger(HtmlTemplatesBean.class);
	private Properties props;

	/**
	 * Reads the properties defined in the htmlTemplates.properties file.
	 * 
	 * @throws IOException
	 */
	public HtmlTemplatesBean() throws IOException {
		InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("htmlTemplates.properties");
		props = new Properties();
		props.load(inStream);
		logger.debug("Initialization of HtmlTemplates bean is complete.");
	}

	/**
	 * Returns an HTML template ( defined in the htmlTemplates.properties file ) and replaces
	 * the placeholders in the template with property values specified in the passed Map.
	 * 
	 * @param property the html template to process
	 * @param params the placeholder-value mapping information
	 * @return template with the placeholders replaced with the actual value
	 */
	public String getProperty(String property, Map<String,String> params) {
		String template = props.getProperty(property);
		Set<String> keySet = params.keySet();
		Iterator<String> iterator = keySet.iterator();
		String returnString = template;
		while(iterator.hasNext()) {
			String key = iterator.next();
			returnString = StringUtils.replace(returnString, "{" + key + "}", params.get(key));
		}
		return returnString;
	}
}
