package config;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static Properties properties;

    static {
        try {
            String env=System.getProperty("env");
            if(env==null || env.isEmpty()){
                env="sit"; //default environment
            }
            InputStream fis = ConfigManager.class.getClassLoader().getResourceAsStream("config-"+env+".properties");
            properties = new Properties();
            properties.load(fis);
            System.out.println("Loaded Environment: "+env);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}

/*Reads Properties
I implemented environment-driven configuration management using system properties.
The framework dynamically loads environment-specific config files based on runtime parameters, enabling flexible execution
across SIT, PreProd, and Production without code changes.
 */