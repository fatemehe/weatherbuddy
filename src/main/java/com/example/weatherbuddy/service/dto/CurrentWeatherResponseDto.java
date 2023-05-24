package com.example.weatherbuddy.service.dto;

import com.example.weatherbuddy.thirdparty.dto.*;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CurrentWeatherResponseDto {

    private Double feelsLike;

    private Double latitude;

    private Double longitude;

    private Double tempMin;

    private Double tempMax;

    private Double pressure;

    private Double humidity;

    private Double seaLevel;

    private Double groundLevel;

    private Double timezone;

    private Map<String, String> metadata;

}
