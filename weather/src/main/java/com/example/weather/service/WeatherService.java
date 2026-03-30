package com.example.weather.service;

import com.example.weather.api.dto.WeatherResponse;
import java.time.Instant;
import java.util.Locale;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private static final String[] DESCRIPTIONS = {
        "Clear sky", "Scattered clouds", "Overcast", "Light rain", "Breezy", "Humid"
    };

    public WeatherResponse getWeather(String city) {
        int seed = Math.floorMod(city.toLowerCase(Locale.ROOT).hashCode(), 10_000);

        double temperature = 10.0 + (seed % 2500) / 100.0; // 10.0 .. 34.99
        int humidity = 40 + (seed % 60); // 40 .. 99
        double windSpeed = 0.5 + (seed % 500) / 100.0; // 0.5 .. 5.49 m/s
        int pressure = 1000 + (seed % 60); // 1000 .. 1059 hPa
        String description = DESCRIPTIONS[seed % DESCRIPTIONS.length];

        return new WeatherResponse(
                city.trim(),
                temperature,
                humidity,
                windSpeed,
                pressure,
                description,
                Instant.now(),
                "demo");
    }
}

