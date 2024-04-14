package api.models;

import lombok.Data;

import java.util.List;

@Data
public class OpenWeatherPojo {
    public CoordPojo coord;
    public List<WeatherPojo> weather;
    public String base;
    public MainPojo main;
    public Integer visibility;
    public WindPojo wind;
    public CloudsPojo clouds;
    public Integer dt;
    public SysPojo sys;
    public Integer timezone;
    public Integer id;
    public String name;
    public Integer cod;
}
