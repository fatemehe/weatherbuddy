package com.example.weatherbuddy.service;

import com.example.weatherbuddy.controller.dto.CurrentWeatherRequestDto;
import com.example.weatherbuddy.controller.dto.ForecastRequestDto;
import com.example.weatherbuddy.controller.dto.HistoryRequestDto;
import com.example.weatherbuddy.exception.WeatherClientException;
import com.example.weatherbuddy.thirdparty.OpenWeatherClient;
import com.example.weatherbuddy.thirdparty.dto.OpenWeatherCurrentWeatherResponseDto;
import com.example.weatherbuddy.thirdparty.dto.OpenWeatherForecastResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenWeatherService implements WeatherDataProvider {

    private final OpenWeatherClient openWeatherClient;

    @Value("${open.weather.api-key}")
    private String openWeatherApiKey;

    @Override
    public OpenWeatherCurrentWeatherResponseDto getCurrentWeather(CurrentWeatherRequestDto requestDto) {
        try {
            return openWeatherClient.currentWeather(
                    String.valueOf(requestDto.getLatitude()),
                    String.valueOf(requestDto.getLongitude()),
                    openWeatherApiKey
            );
        } catch (Exception e) {
            throw new WeatherClientException("error occurred in weather provider");
        }

    }

    @Override
    public String getHistory(HistoryRequestDto requestDto) {
        try {
            return openWeatherClient.history(
                    String.valueOf(requestDto.getLatitude()),
                    String.valueOf(requestDto.getLongitude()),
                    "hour",
                    openWeatherApiKey
            );
        } catch (Exception e) {
            throw new WeatherClientException("error occurred in weather provider");
        }

    }

    @Override
    public OpenWeatherForecastResponseDto forecast(ForecastRequestDto requestDto) {
        try {
            return openWeatherClient.forecast(
                    String.valueOf(requestDto.getLatitude()),
                    String.valueOf(requestDto.getLongitude()),
                    openWeatherApiKey
            );
        } catch (Exception e) {
            throw new WeatherClientException("error occurred in weather provider");
        }
    }


}
