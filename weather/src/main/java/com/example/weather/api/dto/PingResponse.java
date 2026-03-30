package com.example.weather.api.dto;

import java.time.Instant;

public record PingResponse(String status, String service, Instant time) {}

