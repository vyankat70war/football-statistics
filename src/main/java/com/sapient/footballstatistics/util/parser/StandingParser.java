package com.sapient.footballstatistics.util.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import com.sapient.footballstatistics.model.Standing;
import com.sapient.footballstatistics.util.JsonCustomDeserializer;

import java.lang.reflect.Type;
import java.util.Set;

public class StandingParser {
    private Gson gson;
    private Type type;

    private static StandingParser countryGson;

    public Gson getGson() {
        return gson;
    }

    public Type getType() {
        return type;
    }

    public StandingParser invoke() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        JsonDeserializer<Standing> countryJsonDeserializer = JsonCustomDeserializer.standingJsonDeserializer;
        gsonBuilder.registerTypeAdapter(Standing.class, countryJsonDeserializer);
        gson = gsonBuilder.create();
        type = new TypeToken<Set<Standing>>() {
        }.getType();
        return this;
    }

    public static Set<Standing> getStandings(String countries) {
        if(countryGson == null){
            countryGson = new StandingParser().invoke();
        }
        Gson gson = countryGson.getGson();
        Type type = countryGson.getType();
        return gson.fromJson(countries, type);
    }
}
