package dataBase.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertyLoader {
    private static final Logger LOGGER = Logger.getLogger(PropertyLoader.class.getName());

    private PropertyLoader() {
    }

    public static Properties readPropertiesFile(String filename) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(filename)) {
            properties.load(input);
            LOGGER.info("Properties file loaded successfully: " + filename);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error loading properties file: " + filename, ex);
        }
        return properties;
    }

    public static Properties loadProperties() {
        return PropertyLoader.readPropertiesFile("src/main/resources/config.properties");
    }
}
