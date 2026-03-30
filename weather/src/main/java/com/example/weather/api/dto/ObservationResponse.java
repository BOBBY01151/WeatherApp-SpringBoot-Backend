package com.example.weather.api.dto;

import java.time.Instant;

public record ObservationResponse(
        long id,
        String city,
        Double latitude,
        Double longitude,
        double temperature,
        int humidity,
        double windSpeed,
        int pressure,
        Integer weatherCode,
        String description,
        Instant recordedAt,
        String source,
        Instant createdAt) {}
