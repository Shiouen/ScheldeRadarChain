package be.kdg.schelderadarchain.generator.utility;

import com.google.gson.Gson;

/**
 * Created by Cas on 9/11/2015.
 */
public class JsonConverter {

    public static <T> T fromObject(String json, Class<T> classOfT){
        return new Gson().fromJson(json, classOfT);
    }

}
