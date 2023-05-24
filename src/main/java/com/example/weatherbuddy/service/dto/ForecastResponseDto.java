package com.example.weatherbuddy.service.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ForecastResponseDto {
    private List<Forecast> forecasts;

    private Map<String, String> metadata;
}
