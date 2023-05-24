package com.example.weatherbuddy.thirdparty.dto;

import lombok.Data;

@Data
public class CurrentWeatherWeather {
    private Long id;
    private String main;
    private String description;
    private String icon;
}
