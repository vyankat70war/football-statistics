package com.sapient.footballstatistics;

import com.sapient.footballstatistics.model.Standing;
import com.sapient.footballstatistics.service.FootballStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatisticsController {

    @Autowired
    private FootballStatisticsService statisticsService;

    @GetMapping("/standing/{country}/{league}/{team}")
    public ResponseEntity<Object> getStanding(@PathVariable("country") String country,
                                                @PathVariable("league") String league,
                                                @PathVariable("team") String team) {
        ResponseEntity<Object> responseEntity = null;
        Standing standing = statisticsService.getStandingForTeam(country, league, team);
        if(standing != null){
            responseEntity = new ResponseEntity<>(standing, HttpStatus.FOUND);
        }else {
            responseEntity = new ResponseEntity<>("No data found", HttpStatus.OK);
        }

        return responseEntity;
    }
}
