package com.example.weatherbuddy.thirdparty.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CurrentWeatherMain {

    private Double temp;

    @JsonProperty(value = "feels_like")
    private Double feelsLike;

    @JsonProperty(value = "temp_min")
    private Double tempMin;

    @JsonProperty(value = "temp_max")
    private Double tempMax;

    private Double pressure;

    private Double humidity;

    @JsonProperty(value = "sea_level")
    private Double seaLevel;

    @JsonProperty(value = "grnd_level")
    private Double groundLevel;
}
