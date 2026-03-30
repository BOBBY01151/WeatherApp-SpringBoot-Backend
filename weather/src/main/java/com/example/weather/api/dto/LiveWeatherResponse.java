package com.example.weather.api.dto;

import java.time.Instant;

public record LiveWeatherResponse(
        double temperature,
        int humidity,
        double windSpeed,
        int pressure,
        Integer weatherCode,
        Instant recordedAt,
        String source) {}

