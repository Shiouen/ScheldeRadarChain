package be.kdg.schelderadarchain.generator.backend.utility;

import com.google.gson.Gson;

/**
 * Utility class that converts a json string to an object
 *
 * @author Cas Decelle
 */

public class JsonConverter {

    public static <T> T fromJson(String json, Class<T> classOfT){
        return new Gson().fromJson(json, classOfT);
    }

}
