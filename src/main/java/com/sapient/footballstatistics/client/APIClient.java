package com.sapient.footballstatistics.client;

import com.sapient.footballstatistics.model.Country;
import com.sapient.footballstatistics.model.League;
import com.sapient.footballstatistics.model.Standing;
import com.sapient.footballstatistics.util.parser.CountryParser;
import com.sapient.footballstatistics.util.parser.LeagueParser;
import com.sapient.footballstatistics.util.parser.StandingParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Component
public class APIClient {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${football.api.url}")
    private String apiUrl;

    @Value("${football.api.key}")
    private String apiKey;

    public Set<Country> getCountryList() {
        String countries = restTemplate.getForObject(apiUrl + "?action=get_countries&APIkey=" + apiKey, String.class);
        Set<Country> countryList = CountryParser.getCountries(countries);
        return countryList;
    }

    public Set<League> getLeagueForCountry(int countryId) {
        String leagues = restTemplate.getForObject(apiUrl + "?action=get_leagues&country_id=" + countryId + "&APIkey=" + apiKey, String.class);
        return LeagueParser.getLeagues(leagues);
    }

    public Set<Standing> getTeamStandingInLeague(int leagueId){
        String standings = restTemplate.getForObject(apiUrl + "?action=get_standings&league_id=" + leagueId + "&APIkey=" + apiKey, String.class);
        return StandingParser.getStandings(standings);
    }

}
