package com.sapient.footballstatistics.model;

import lombok.Data;

public class Standing {
    private Country country;
    private League league;
    private Team team;
    private int position;

    public Standing(Country country, League league, Team team, int position) {
        this.country = country;
        this.league = league;
        this.team = team;
        this.position = position;
    }

    public Country getCountry() {
        return country;
    }

    public League getLeague() {
        return league;
    }

    public Team getTeam() {
        return team;
    }

    public int getPosition() {
        return position;
    }
}
