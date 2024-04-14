package api.models;

import lombok.Data;

@Data
public class WeatherPojo {
    public Integer id;
    public String main;
    public String description;
    public String icon;
}
