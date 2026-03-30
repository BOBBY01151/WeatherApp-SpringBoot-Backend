package com.example.weather.api.dto;

import java.time.Instant;

public record WeatherResponse(
        String city,
        double temperature,
        int humidity,
        double windSpeed,
        int pressure,
        String description,
        Instant recordedAt,
        String source) {}

