package com.sapient.footballstatistics.client;

import com.sapient.footballstatistics.model.League;
import com.sapient.footballstatistics.model.Standing;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Set;

public class APIClientTest {
    private APIClient apiClient;
    @Before
    public void setUp() {
        apiClient = new APIClient();
        ReflectionTestUtils.setField(apiClient, "apiUrl", "https://apiv2.apifootball.com/");
        ReflectionTestUtils.setField(apiClient, "apiKey", "9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978");
    }

    @Test
    public void name() {
        apiClient.getCountryList();
    }

    @Test
    public void test_leagues() {
        Set<League> leagues = apiClient.getLeagueForCountry(41);
    }

    @Test
    public void test_standing() {
        Set<Standing> standing = apiClient.getTeamStandingInLeague(149);
    }
}
