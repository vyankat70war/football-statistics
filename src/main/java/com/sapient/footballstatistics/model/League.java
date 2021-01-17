package com.sapient.footballstatistics.model;

public class League {
    private int leagueId;
    private String leagueName;

    public League(int leagueId, String leagueName) {
        this.leagueId = leagueId;
        this.leagueName = leagueName;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }
}
