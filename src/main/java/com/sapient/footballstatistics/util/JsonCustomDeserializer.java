package com.sapient.footballstatistics.util;

import com.google.gson.*;
import com.sapient.footballstatistics.model.Country;
import com.sapient.footballstatistics.model.League;
import com.sapient.footballstatistics.model.Standing;
import com.sapient.footballstatistics.model.Team;

import java.lang.reflect.Type;

public class JsonCustomDeserializer {
    public static JsonDeserializer<Country> countryJsonDeserializer = new JsonDeserializer<Country>() {
        @Override
        public Country deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            return new Country(
                    Integer.parseInt(jsonObject.get("country_id").getAsString()),
                    jsonObject.get("country_name").getAsString()
            );
        }
    };


    public static JsonDeserializer<League> competitionJsonDeserializer = new JsonDeserializer<League>() {
        @Override
        public League deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            return new League(
                    Integer.parseInt(jsonObject.get("league_id").getAsString()),
                    jsonObject.get("league_name").getAsString()
            );
        }
    };


    public static JsonDeserializer<Standing> standingJsonDeserializer = (jsonElement, type, jsonDeserializationContext) -> {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        League league = new League(
                jsonObject.get("league_id").getAsInt(),
                jsonObject.get("league_name").getAsString()
        );

        JsonElement country_id = jsonObject.get("country_id");
        Country country = null;
        if(country_id != null){
            country = new Country(
                    country_id.getAsInt(),
                    jsonObject.get("country_name").getAsString()
            );
        }else {
            country = new Country(
                    0,
                    jsonObject.get("country_name").getAsString()
            );
        }


        Team team = new Team(
                jsonObject.get("team_id").getAsInt(),
                jsonObject.get("team_name").getAsString()
        );
        int position = jsonObject.get("overall_league_position").getAsInt();

        return new Standing(country, league, team, position);
    };
}
