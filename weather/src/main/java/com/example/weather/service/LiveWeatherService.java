package com.example.weather.service;

import com.example.weather.api.dto.LiveWeatherResponse;
import com.fasterxml.jackson.databind.JsonNode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LiveWeatherService {

    private final RestClient restClient;

    public LiveWeatherService() {
        this.restClient = RestClient.builder().baseUrl("https://api.open-meteo.com").build();
    }

    public LiveWeatherResponse fetchCurrent(double latitude, double longitude) {
        try {
            JsonNode root = restClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/v1/forecast")
                            .queryParam("latitude", latitude)
                            .queryParam("longitude", longitude)
                            .queryParam(
                                    "current",
                                    "temperature_2m,relative_humidity_2m,surface_pressure,wind_speed_10m,weather_code")
                            .queryParam("temperature_unit", "celsius")
                            .queryParam("wind_speed_unit", "ms")
                            .queryParam("pressure_unit", "hPa")
                            .queryParam("timezone", "auto")
                            .build())
                    .retrieve()
                    .body(JsonNode.class);

            if (root == null || root.isMissingNode()) {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Live weather provider returned no data.");
            }

            JsonNode current = root.path("current");
            double temperature = current.path("temperature_2m").asDouble(Double.NaN);
            int humidity = current.path("relative_humidity_2m").asInt(0);
            double windSpeed = current.path("wind_speed_10m").asDouble(Double.NaN);
            int pressure = (int) Math.round(current.path("surface_pressure").asDouble(Double.NaN));
            Integer weatherCode = current.hasNonNull("weather_code") ? current.path("weather_code").asInt() : null;

            if (!Double.isFinite(temperature) || !Double.isFinite(windSpeed) || pressure == 0) {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Live weather provider returned invalid data.");
            }

            Instant recordedAt = parseRecordedAt(root, current);

            return new LiveWeatherResponse(
                    temperature,
                    humidity,
                    windSpeed,
                    pressure,
                    weatherCode,
                    recordedAt,
                    "open-meteo");
        } catch (RestClientException ex) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Live weather provider unavailable.", ex);
        }
    }

    private static Instant parseRecordedAt(JsonNode root, JsonNode current) {
        String time = current.path("time").asText(null);
        int offsetSeconds = root.path("utc_offset_seconds").asInt(0);
        if (time == null || time.isBlank()) {
            return Instant.now();
        }

        try {
            LocalDateTime local = LocalDateTime.parse(time);
            ZoneOffset offset = ZoneOffset.ofTotalSeconds(offsetSeconds);
            return OffsetDateTime.of(local, offset).toInstant();
        } catch (DateTimeParseException ex) {
            return Instant.now();
        }
    }
}

