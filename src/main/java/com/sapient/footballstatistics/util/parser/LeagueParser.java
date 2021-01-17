package com.sapient.footballstatistics.util.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import com.sapient.footballstatistics.model.Country;
import com.sapient.footballstatistics.model.League;
import com.sapient.footballstatistics.util.JsonCustomDeserializer;

import java.lang.reflect.Type;
import java.util.Set;

public class LeagueParser {
    private Gson gson;
    private Type type;

    private static LeagueParser countryGson;

    public Gson getGson() {
        return gson;
    }

    public Type getType() {
        return type;
    }

    public LeagueParser invoke() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        JsonDeserializer<League> countryJsonDeserializer = JsonCustomDeserializer.competitionJsonDeserializer;
        gsonBuilder.registerTypeAdapter(League.class, countryJsonDeserializer);
        gson = gsonBuilder.create();
        type = new TypeToken<Set<League>>() {
        }.getType();
        return this;
    }

    public static Set<League> getLeagues(String leagues) {
        if(countryGson == null){
            countryGson = new LeagueParser().invoke();
        }
        Gson gson = countryGson.getGson();
        Type type = countryGson.getType();
        return gson.fromJson(leagues, type);
    }
}
