package org.jasig.cas.client.util;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 从配置文件cas.properties中读取参数。
 * 
 * @author zhangxin 2016/1/28
 * 
 */
public class CasClientConfig {

    protected static final Logger logger = LoggerFactory
	    .getLogger(CasClientConfig.class);

    private static String default_config = "/org/jasig/cas/client/util/cas.properties";
    public static String custom_config = "/cas.properties";

    private static Properties config = new Properties();

    static {
	try {
	    Class config_class = Class
		    .forName("org.jasig.cas.client.util.CasClientConfig");

	    InputStream is = config_class.getResourceAsStream(custom_config);
	    if (is != null) {
		logger.debug("Successfully loaded custom properties file from classpath");
		config.load(is);
	    } else {
		is = config_class.getResourceAsStream(default_config);
		if (is != null) {
		    logger.debug("Successfully loaded default properties file from classpath");
		    config.load(is);
		}
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static String getProperty(String key) {
	logger.debug("Fetching property [" + key + "="
		+ config.getProperty(key) + "]");
	String value = config.getProperty(key);
	return value == null ? value : value.trim();
    }

    public static String getProperty(String key, String defaultValue) {
	logger.debug("Fetching property [" + key + "="
		+ config.getProperty(key) + ",defaultValue=" + defaultValue
		+ "]");
	String value = config.getProperty(key);
	if (value == null) {
	    return defaultValue;
	}
	return value.trim();
    }

    public static boolean getBooleanProperty(String name) {
	return getBooleanProperty(name, false);
    }

    public static boolean getBooleanProperty(String name, boolean defaultValue) {
	String value = getProperty(name);

	if (value == null) {
	    return defaultValue;
	}
	return new Boolean(value).booleanValue();
    }

    public static int getIntProperty(String name) {
	return getIntProperty(name, 0);
    }

    public static int getIntProperty(String name, int defaultValue) {
	String value = getProperty(name);

	if (value == null) {
	    return defaultValue;
	}
	return new Integer(value).intValue();
    }

    public static Enumeration keys() {
	return config.keys();
    }

    public static Properties getPropertiesStartingWith(String startingWith) {
	Properties props = new Properties();
	for (Enumeration it = config.keys(); it.hasMoreElements();) {
	    String key = (String) it.nextElement();
	    props.put(key, config.get(key));
	}
	return props;
    }

    public static void setUploadsDir(String path) {
	if ("${webapp.context}".equals(config.getProperty("uploads.dir")))
	    config.setProperty("uploads.dir", path);
    }

    public static void setThemesDir(String path) {
	if ("${webapp.context}".equals(config.getProperty("themes.dir")))
	    config.setProperty("themes.dir", path);
    }
}