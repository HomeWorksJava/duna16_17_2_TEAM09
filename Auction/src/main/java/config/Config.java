package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static Properties properties;
    
    public static void initConfig() throws IOException {
	try (InputStream in = Config.class.getClassLoader().getResourceAsStream("settings.properties")) {
            properties = new Properties();
	    properties.load(in);
        }
    }
    
    public static String getProperty(String key, String defaultValue) throws IOException {
	if (properties == null) {
	    initConfig();
	}
	return properties.getProperty(key, defaultValue);
    }
}
