package dataBase.models;

import lombok.Data;

@Data
public class ResponseFromDbByCityPojo {
    private String city;
    private double tempMetric;
    private double feelsLike;
    private double tempAvg;
}
