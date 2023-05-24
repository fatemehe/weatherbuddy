package com.example.weatherbuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WeatherbuddyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherbuddyApplication.class, args);
    }

}
