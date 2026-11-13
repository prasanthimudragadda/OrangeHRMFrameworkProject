package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream file = new FileInputStream("src/main/resources/config.properties");
            properties.load(file);
            file.close();
        } catch (IOException e) {
            throw new RuntimeException("Could not read config.properties", e);
        }
    }

    public static String get(String key) {
        String value = System.getProperty(key);
        if(value!=null){
            return value;
        }
        return properties.getProperty(key);
    }
}
