package com.sapient.footballstatistics.service;

import com.sapient.footballstatistics.client.APIClient;
import com.sapient.footballstatistics.model.Country;
import com.sapient.footballstatistics.model.League;
import com.sapient.footballstatistics.model.Standing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FootballStatisticsService {

    @Autowired
    private APIClient apiClient;

    public Standing getStandingForTeam(String countryName, String leagueName, String teamName) {
        Country country = apiClient.getCountryList()
                .stream().filter(c -> c.getCountryName().equalsIgnoreCase(countryName)).findFirst().get();

        League league = apiClient.getLeagueForCountry(country.getCountryId())
                .stream()
                .filter(l -> l.getLeagueName().equalsIgnoreCase(leagueName))
                .findFirst().get();

        Standing standing = apiClient.getTeamStandingInLeague(league.getLeagueId())
                .stream()
                .filter(s -> s.getCountry().getCountryName().equalsIgnoreCase(country.getCountryName()) &&
                        s.getLeague().getLeagueId() == league.getLeagueId() &&
                        s.getTeam().getTeamName().equalsIgnoreCase(teamName)
                )
                .findFirst().orElse(null);

        return standing;
    }
}
