package com.example.weatherbuddy.thirdparty.dto;

import lombok.Data;

import java.util.List;

@Data
public class OpenWeatherForecastResponseDto {

    private String cod;

    private Integer message;

    private Integer cnt;

    private List<ForecastList> list;

    private ForecastCity city;

}
