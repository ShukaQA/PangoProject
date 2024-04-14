package dataBase.dao;

import dataBase.models.ResponseFromDbByCityPojo;

import java.sql.*;

public class GetWeatherDataDAO extends DataBasePool {
    public GetWeatherDataDAO() {
        loadDatabaseProperties();
        loadSQLQuery();
    }

    // Method to retrieve weather data from the database for a given city
    public ResponseFromDbByCityPojo getWeatherData(String city) {
        ResponseFromDbByCityPojo responseFromDbByCityPojo = null;
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://" + dbHost + ":" + port + "/" + dbName, dbUser, password);
             PreparedStatement preparedStatement = connection.prepareStatement(getTemperatureByCityPath)) {

            // Set parameters
            preparedStatement.setString(1, city);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if any data is retrieved
            if (resultSet.next()) {
                responseFromDbByCityPojo = new ResponseFromDbByCityPojo();

                // Set data from the ResultSet to WeatherData fields
                responseFromDbByCityPojo.setCity(resultSet.getString("city"));
                responseFromDbByCityPojo.setTempMetric(resultSet.getDouble("temp_metric"));
                responseFromDbByCityPojo.setFeelsLike(resultSet.getDouble("feels_like"));
                responseFromDbByCityPojo.setTempAvg(resultSet.getDouble("temp_avg"));
            }
        } catch (SQLException e) {
            LOGGER.info("Can't get weather data from DB");
        }

        return responseFromDbByCityPojo;
    }
}
