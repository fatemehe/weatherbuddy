package com.example.weatherbuddy.controller;

import com.example.weatherbuddy.controller.dto.CurrentWeatherRequestDto;
import com.example.weatherbuddy.controller.dto.ForecastRequestDto;
import com.example.weatherbuddy.controller.dto.HistoryRequestDto;
import com.example.weatherbuddy.service.OpenWeatherService;
import com.example.weatherbuddy.thirdparty.dto.OpenWeatherCurrentWeatherResponseDto;
import com.example.weatherbuddy.thirdparty.dto.OpenWeatherForecastResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class WeatherController {
    private final OpenWeatherService openWeatherService;

    @PostMapping("/current-weather")
    public Mono<OpenWeatherCurrentWeatherResponseDto> currentWeather(@Valid @RequestBody CurrentWeatherRequestDto requestDto) {
        OpenWeatherCurrentWeatherResponseDto response = openWeatherService.getCurrentWeather(requestDto);
        return Mono.just(response);
    }

    @PostMapping("/history")
    public Mono<String> history(@Valid @RequestBody HistoryRequestDto requestDto) {
        String response = openWeatherService.getHistory(requestDto);
        return Mono.just(response);
    }

    @PostMapping("/forecast")
    public Mono<OpenWeatherForecastResponseDto> forecast(@Valid @RequestBody ForecastRequestDto requestDto) {
        OpenWeatherForecastResponseDto response = openWeatherService.forecast(requestDto);
        return Mono.just(response);
    }
}
