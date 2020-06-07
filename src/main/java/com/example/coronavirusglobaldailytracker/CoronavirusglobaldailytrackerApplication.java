package com.example.coronavirusglobaldailytracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoronavirusglobaldailytrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoronavirusglobaldailytrackerApplication.class, args);
    }

}
