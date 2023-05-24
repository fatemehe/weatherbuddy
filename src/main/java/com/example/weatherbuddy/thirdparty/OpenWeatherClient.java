package com.example.weatherbuddy.thirdparty;

import com.example.weatherbuddy.thirdparty.dto.OpenWeatherCurrentWeatherResponseDto;
import com.example.weatherbuddy.thirdparty.dto.OpenWeatherForecastResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "weather-proxy",
        url = "https://api.openweathermap.org/data/2.5/",
        configuration = FeignClientsConfiguration.class)
public interface OpenWeatherClient {
    @GetMapping(value = "/weather")
    OpenWeatherCurrentWeatherResponseDto currentWeather(
            @RequestParam(value = "lat") String lat,
            @RequestParam(value = "lon") String lon,
            @RequestParam(value = "appid") String appid
    );

    @GetMapping(value = "/history/city")
    String history(
            @RequestParam(value = "lat") String lat,
            @RequestParam(value = "lon") String lon,
            @RequestParam(value = "type") String type,
            @RequestParam(value = "appid") String appid
    );

    @GetMapping(value = "/forecast")
    OpenWeatherForecastResponseDto forecast(
            @RequestParam(value = "lat") String lat,
            @RequestParam(value = "lon") String lon,
            @RequestParam(value = "appid") String appid
    );
}
