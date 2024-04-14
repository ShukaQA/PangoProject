package dataBase.dao;

import dataBase.util.PropertyLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBasePool {

    protected static final Logger LOGGER = Logger.getLogger(PropertyLoader.class.getName());

    public String dbHost;
    public String dbName;
    public String dbUser;
    public String password;
    public String port;
    public String getTemperatureByCityPath = "src/main/java/dataBase/sql_query/getTemperatureByCity.sql";
    public String addDataInWeatherFromApiTablePath = "src/main/java/dataBase/sql_query/addDataInWeatherFromApiTable.sql";
    public String createDbTablesPath = "src/main/java/dataBase/sql_query/createDbTables.sql";


    public void loadDatabaseProperties() {
        Properties properties = PropertyLoader.loadProperties();
        dbHost = properties.getProperty("dbHost");
        dbName = properties.getProperty("dbName");
        dbUser = properties.getProperty("dbUser");
        password = properties.getProperty("password");
        port = properties.getProperty("port");
    }

    public void loadSQLQuery() {
        try {
            // Load SQL query for retrieving weather data
            Path path = Paths.get(getTemperatureByCityPath);
            getTemperatureByCityPath = Files.readString(path);
            LOGGER.info("SQL query loaded successfully for retrieving weather data");

            // Load SQL query for inserting weather data
            path = Paths.get(addDataInWeatherFromApiTablePath);
            addDataInWeatherFromApiTablePath = Files.readString(path);
            LOGGER.info("SQL query loaded successfully for inserting weather data");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading SQL query", e);
        }
    }
}
