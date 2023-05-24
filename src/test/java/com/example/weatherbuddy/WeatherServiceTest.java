package com.example.weatherbuddy;

import com.example.weatherbuddy.controller.dto.CurrentWeatherRequestDto;
import com.example.weatherbuddy.controller.dto.ForecastRequestDto;
import com.example.weatherbuddy.controller.dto.HistoryRequestDto;
import com.example.weatherbuddy.exception.WeatherClientException;
import com.example.weatherbuddy.service.OpenWeatherService;
import com.example.weatherbuddy.thirdparty.OpenWeatherClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

    @Mock
    private OpenWeatherClient openWeatherClient;

    private OpenWeatherService openWeatherService;

    @BeforeEach
    public void setup() {
        openWeatherService = new OpenWeatherService(openWeatherClient);
    }

    @Test
    public void callCurrentWeather_whenPassTheWrongLongitude_ThrowsWeatherClientException(){
        Double longitude = 300.0;
        Double latitude = 20.0;
        when(openWeatherClient.currentWeather(anyString(), eq(String.valueOf(longitude)), anyString()))
                .thenThrow(new RuntimeException());
        assertThrows(WeatherClientException.class,
                ()->openWeatherService.getCurrentWeather(new CurrentWeatherRequestDto(longitude, latitude)));

    }

    @Test
    public void callCurrentWeather_whenPassTheWrongLatitude_ThrowsWeatherClientException(){
        Double latitude = 300.0;
        Double longitude = 20.0;
        when(openWeatherClient.currentWeather(eq(String.valueOf(latitude)), anyString(), anyString()))
                .thenThrow(new RuntimeException());
        assertThrows(WeatherClientException.class,
                ()->openWeatherService.getCurrentWeather(new CurrentWeatherRequestDto(longitude, latitude)));
    }

    @Test
    public void calForecast_whenPassTheWrongLongitude_ThrowsWeatherClientException(){
        Double longitude = 300.0;
        Double latitude = 20.0;
        when(openWeatherClient.forecast(anyString(), eq(String.valueOf(longitude)), anyString()))
                .thenThrow(new RuntimeException());
        assertThrows(WeatherClientException.class,
                ()->openWeatherService.forecast(new ForecastRequestDto(longitude, latitude)));

    }

    @Test
    public void callForecast_whenPassTheWrongLatitude_ThrowsWeatherClientException(){
        Double latitude = 300.0;
        Double longitude = 20.0;
        when(openWeatherClient.forecast(eq(String.valueOf(latitude)), anyString(), anyString()))
                .thenThrow(new RuntimeException());
        assertThrows(WeatherClientException.class,
                ()->openWeatherService.forecast(new ForecastRequestDto(longitude, latitude)));
    }

    @Test
    public void calHistory_whenPassTheWrongLongitude_ThrowsWeatherClientException(){
        Double longitude = 300.0;
        Double latitude = 20.0;
        when(openWeatherClient.history(anyString(), eq(String.valueOf(longitude)),anyString(), anyString()))
                .thenThrow(new RuntimeException());
        assertThrows(WeatherClientException.class,
                ()->openWeatherService.getHistory(new HistoryRequestDto(longitude, latitude)));

    }

    @Test
    public void callHistory_whenPassTheWrongLatitude_ThrowsWeatherClientException(){
        Double latitude = 300.0;
        Double longitude = 20.0;
        when(openWeatherClient.history(eq(String.valueOf(latitude)), anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException());
        assertThrows(WeatherClientException.class,
                ()->openWeatherService.getHistory(new HistoryRequestDto(longitude, latitude)));
    }

}
