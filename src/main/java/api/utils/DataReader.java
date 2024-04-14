package api.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;

public class DataReader {
    public DataReader() {
    }

    public static <T> T writeToModel(String a, Class<?> t) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(a, (Type) t);
    }
}
