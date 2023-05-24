package com.example.weatherbuddy.thirdparty.dto;

import lombok.Data;

import java.util.List;

@Data
public class OpenWeatherCurrentWeatherResponseDto {

    private WeatherCoordination coord;

    private List<CurrentWeatherWeather> weather;

    private String base;

    private CurrentWeatherMain main;

    private Double visibility;

    private WeatherWind wind;

    private WeatherClouds clouds;

    private String dt;

    private CurrentWeatherSystem sys;

    private Double timezone;

    private Long id;

    private String name;

    private long cod;
}
