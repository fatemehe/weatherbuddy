package com.example.weatherbuddy.service;

import com.example.weatherbuddy.controller.dto.CurrentWeatherRequestDto;
import com.example.weatherbuddy.controller.dto.ForecastRequestDto;
import com.example.weatherbuddy.controller.dto.HistoryRequestDto;
import com.example.weatherbuddy.thirdparty.dto.OpenWeatherCurrentWeatherResponseDto;
import com.example.weatherbuddy.thirdparty.dto.OpenWeatherForecastResponseDto;

public interface WeatherDataProvider {
    OpenWeatherCurrentWeatherResponseDto getCurrentWeather(CurrentWeatherRequestDto requestDto);

    String getHistory(HistoryRequestDto requestDto);

    OpenWeatherForecastResponseDto forecast(ForecastRequestDto requestDto);
}
