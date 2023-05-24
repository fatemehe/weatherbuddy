package com.example.weatherbuddy.service;

import com.example.weatherbuddy.controller.dto.CurrentWeatherRequestDto;
import com.example.weatherbuddy.controller.dto.ForecastRequestDto;
import com.example.weatherbuddy.controller.dto.HistoryRequestDto;
import com.example.weatherbuddy.thirdparty.dto.OpenWeatherCurrentWeatherResponseDto;
import com.example.weatherbuddy.thirdparty.dto.OpenWeatherForecastResponseDto;

public class BitWeatherService implements WeatherDataProvider{
    @Override
    public OpenWeatherCurrentWeatherResponseDto getCurrentWeather(CurrentWeatherRequestDto requestDto) {
        return null;
    }

    @Override
    public String getHistory(HistoryRequestDto requestDto) {
        return null;
    }

    @Override
    public OpenWeatherForecastResponseDto forecast(ForecastRequestDto requestDto) {
        return null;
    }
}
