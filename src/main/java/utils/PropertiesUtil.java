package utils;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class PropertiesUtil {
    private static Properties properties = new Properties();

    static {
        try (InputStream input = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                System.err.println("Файл application.properties не найден.");
            }
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
            ex.printStackTrace();

        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
