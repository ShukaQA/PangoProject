package api.utils;

import dataBase.util.PropertyLoader;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Properties;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

public class ApiUtil {
    protected static final Logger LOGGER = Logger.getLogger(PropertyLoader.class.getName());
    Properties properties = PropertyLoader.loadProperties();
    String baseUri = properties.getProperty("base_uri");

    public Response getResponseByCity(String city) {
        RestAssured.baseURI = baseUri;

        // Log the constructed URL
        String url = RestAssured.baseURI + "/data/2.5/weather?q=" + city.replaceAll("\\s+", "") + "&appid=" + properties.getProperty("key") + "&lang=en&units=metric";
        LOGGER.info("URL: " + url);

        return given()
                .queryParam("q", city)
                .queryParam("appid", properties.getProperty("key"))
                .queryParam("lang", "en")
                .queryParam("units", "metric")
                .when()
                .get("/data/2.5/weather")
                .then()
                .extract()
                .response();
    }
}
