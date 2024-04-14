package dataBase.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.logging.Level;

public class OpenWeatherDAO extends DataBasePool {

    public OpenWeatherDAO() {
        loadDatabaseProperties();
        loadSQLQuery();
    }

    // Method to create the weatherFromApi table if it does not already exist
    public void createWeatherTableIfNotExists() {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://" + dbHost + ":" + port + "/" + dbName, dbUser, password)) {

            // Load SQL query from file
            Path path = Paths.get(createDbTablesPath);
            String createTableSQL = Files.readString(path);

            try (Statement statement = connection.createStatement()) {
                // Execute the SQL statement
                statement.executeUpdate(createTableSQL);

                LOGGER.info("weatherFromApi table created or already exists");
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading SQL query", e);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating weatherFromApi table", e);
        }
    }

    // Method to insert weather data into the weatherFromApi table using loaded query
    public void insertWeatherData(String city, double temperature, double feelsLike, double tempAvg) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://" + dbHost + ":" + port + "/" + dbName, dbUser, password);
             PreparedStatement preparedStatement = connection.prepareStatement(addDataInWeatherFromApiTablePath)) {

            // Set parameters
            preparedStatement.setString(1, city);
            preparedStatement.setDouble(2, temperature);
            preparedStatement.setDouble(3, feelsLike);
            preparedStatement.setDouble(4, tempAvg);

            // Execute the SQL statement
            int rowsAffected = preparedStatement.executeUpdate();

            LOGGER.info(rowsAffected + " row(s) inserted into weatherFromApi table for " + city + " city");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error inserting weather data into weatherFromApi table for " + city + " city", e);
        }
    }
}
