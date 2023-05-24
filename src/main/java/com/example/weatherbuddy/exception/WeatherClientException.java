package com.example.weatherbuddy.exception;

public class WeatherClientException extends RuntimeException {

    public WeatherClientException(String message) {
        super(message);
    }

    public WeatherClientException() {
    }
}
