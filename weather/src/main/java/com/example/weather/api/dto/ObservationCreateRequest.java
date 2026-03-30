package com.example.weather.api.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.Instant;

public record ObservationCreateRequest(
        @NotBlank @Size(max = 80) String city,
        @DecimalMin(value = "-90.0") @DecimalMax(value = "90.0") Double latitude,
        @DecimalMin(value = "-180.0") @DecimalMax(value = "180.0") Double longitude,
        double temperature,
        int humidity,
        double windSpeed,
        int pressure,
        @Min(0) @Max(99) Integer weatherCode,
        @Size(max = 255) String description,
        Instant recordedAt,
        @Size(max = 40) String source) {}
