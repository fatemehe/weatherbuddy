package com.example.weatherbuddy.thirdparty.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ForecastList {
    private String dt;
    private ForecastMain main;
    private List<ForecastWeather> weather;
    private WeatherClouds clouds;
    private WeatherWind wind;
    private String visibility;
    private String pop;
    private ForecastSystem sys;
    @JsonProperty(value = "dt_txt")
    private String dtTxt;
}
