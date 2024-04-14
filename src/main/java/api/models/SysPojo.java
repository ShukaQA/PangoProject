package api.models;

import lombok.Data;

@Data
public class SysPojo {
    public Integer type;
    public Integer id;
    public String country;
    public Integer sunrise;
    public Integer sunset;
}
