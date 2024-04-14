package api.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class MainPojo {
    public Float temp;
    @SerializedName("feels_like")
    public Float feelsLike;
    public Float tempMin;
    public Float tempMax;
    public Integer pressure;
    public Integer humidity;
}
