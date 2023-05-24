package com.example.weatherbuddy.thirdparty.dto;

import lombok.Data;

@Data
public class ForecastCity {

    private Long id;
    private String name;
    private WeatherCoordination coord;
    private String country;
    private Long population;
    private Long timezone;
    private Long sunrise;
    private Long sunset;
}
