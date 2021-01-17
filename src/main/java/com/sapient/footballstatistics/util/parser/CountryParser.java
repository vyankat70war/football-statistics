package com.sapient.footballstatistics.util.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import com.sapient.footballstatistics.model.Country;
import com.sapient.footballstatistics.util.JsonCustomDeserializer;

import java.lang.reflect.Type;
import java.util.Set;

public class CountryParser {
    private Gson gson;
    private Type type;

    private static CountryParser countryParser;

    public Gson getGson() {
        return gson;
    }

    public Type getType() {
        return type;
    }

    public CountryParser invoke() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        JsonDeserializer<Country> countryJsonDeserializer = JsonCustomDeserializer.countryJsonDeserializer;
        gsonBuilder.registerTypeAdapter(Country.class, countryJsonDeserializer);
        gson = gsonBuilder.create();
        type = new TypeToken<Set<Country>>() {
        }.getType();
        return this;
    }

    public static Set<Country> getCountries(String countries) {
        if(countryParser == null){
            countryParser = new CountryParser().invoke();
        }
        Gson gson = countryParser.getGson();
        Type type = countryParser.getType();
        return gson.fromJson(countries, type);
    }
}
