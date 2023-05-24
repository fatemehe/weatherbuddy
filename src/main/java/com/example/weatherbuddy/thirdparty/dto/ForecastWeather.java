package com.example.weatherbuddy.thirdparty.dto;

import lombok.Data;

@Data
public class ForecastWeather {
    private Long id;
    private String main;
    private String description;
    private String icon;
}
