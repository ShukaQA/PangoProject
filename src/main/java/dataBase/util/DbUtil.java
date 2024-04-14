package dataBase.util;

import dataBase.models.ResponseFromDbByCityPojo;

import java.util.List;

public class DbUtil {
    public String getHighestAverageCity(List<ResponseFromDbByCityPojo> weatherDataList) {
        double highestAverage = Double.MIN_VALUE;
        String highestAverageCity = "";

        for (ResponseFromDbByCityPojo responseFromDbByCityPojo : weatherDataList) {
            if (responseFromDbByCityPojo.getTempAvg() > highestAverage) {
                highestAverage = responseFromDbByCityPojo.getTempAvg();
                highestAverageCity = responseFromDbByCityPojo.getCity();
            }
        }

        return highestAverageCity;
    }
}
