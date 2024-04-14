import api.models.MainPojo;
import api.models.OpenWeatherPojo;
import api.utils.ApiUtil;
import cityEnum.CityEnum;
import dataBase.models.ResponseFromDbByCityPojo;
import dataBase.util.BaseTest;
import dataBase.util.DbUtil;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static api.utils.DataReader.writeToModel;


public class WeatherTests extends BaseTest {
    @Test
    public void getCurrentWeatherTest() {
        SoftAssert softAssert = new SoftAssert();
        List<ResponseFromDbByCityPojo> weatherDataList = new ArrayList<>();

        for (CityEnum city : CityEnum.values()) {
            Response response = new ApiUtil().getResponseByCity(city.getCityName());

            // Check status code
            response.then().statusCode(200);

            OpenWeatherPojo openWeatherPojo = writeToModel(response.asString(), OpenWeatherPojo.class);
            MainPojo main = openWeatherPojo.getMain();

            // Insert data into the database
            openWeatherDAO.insertWeatherData(city.getCityName(), main.getTemp(), main.getFeelsLike(), (main.getTemp() + main.getFeelsLike()) / 2);

            // Retrieve data from the database and save it to the list
            ResponseFromDbByCityPojo responseFromDbByCityPojo = getWeatherDataDAO.getWeatherData(city.getCityName());
            weatherDataList.add(responseFromDbByCityPojo);

            softAssert.assertNotNull(responseFromDbByCityPojo, "Response is null for city: " + city.getCityName());
            softAssert.assertEquals(responseFromDbByCityPojo.getCity(), city.getCityName(), "City name mismatch for city: " + city.getCityName());
            softAssert.assertEquals(responseFromDbByCityPojo.getTempMetric(), main.getTemp(), 0.01, "Temperature metric mismatch for city: " + city.getCityName()); // Assuming a tolerance of 0.01 for double comparison
            softAssert.assertEquals(responseFromDbByCityPojo.getFeelsLike(), main.getFeelsLike(), 0.01, "Feels like value mismatch for city: " + city.getCityName()); // Assuming a tolerance of 0.01 for double comparison
            softAssert.assertEquals(responseFromDbByCityPojo.getTempAvg(), (main.getTemp() + main.getFeelsLike()) / 2, 0.01, "Average temperature mismatch for city: " + city.getCityName()); // Assuming a tolerance of 0.01 for double comparison

        }

        // Print the city with the highest average temperature
        String highestAverageCity = new DbUtil().getHighestAverageCity(weatherDataList);
        LOGGER.info("City with the highest average temperature: " + highestAverageCity);

        softAssert.assertAll();

    }


}
