package com.example.weatherbuddy.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForecastRequestDto {
    @NotNull(message = "Longitude is required")
    @Min(value = -180, message = "Longitude should be between -180 and 180")
    @Max(value = 180, message = "Longitude should be between -180 and 180")
    private Double longitude;

    @Min(value = -90, message = "Latitude should be between -180 and 180")
    @Max(value = 90, message = "Latitude should be between -180 and 180")
    @NotNull(message = "Latitude is required")
    private Double latitude;
}
