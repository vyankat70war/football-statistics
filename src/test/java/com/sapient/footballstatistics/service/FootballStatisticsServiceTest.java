package com.sapient.footballstatistics.service;

import com.sapient.footballstatistics.client.APIClient;
import com.sapient.footballstatistics.model.Standing;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class FootballStatisticsServiceTest {
    private FootballStatisticsService statisticsService;

    @Before
    public void setUp() throws Exception {
        statisticsService = new FootballStatisticsService();
        APIClient apiClient = new APIClient();
        ReflectionTestUtils.setField(apiClient, "apiUrl", "https://apiv2.apifootball.com/");
        ReflectionTestUtils.setField(apiClient, "apiKey", "9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978");
        ReflectionTestUtils.setField(statisticsService, "apiClient", apiClient);
    }


    @Test
    public void name() {
        Standing standing = statisticsService.getStandingForTeam("England", "Championship", "Norwich");
        System.out.println(standing);
    }
}
