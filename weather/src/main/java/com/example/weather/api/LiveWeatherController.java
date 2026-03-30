package com.example.weather.api;

import com.example.weather.api.dto.LiveWeatherResponse;
import com.example.weather.service.LiveWeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/live-weather")
public class LiveWeatherController {

    private final LiveWeatherService liveWeatherService;

    public LiveWeatherController(LiveWeatherService liveWeatherService) {
        this.liveWeatherService = liveWeatherService;
    }

    @GetMapping
    public LiveWeatherResponse current(@RequestParam("lat") double latitude, @RequestParam("lon") double longitude) {
        if (latitude < -90.0 || latitude > 90.0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Latitude must be between -90 and 90.");
        }
        if (longitude < -180.0 || longitude > 180.0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Longitude must be between -180 and 180.");
        }

        return liveWeatherService.fetchCurrent(latitude, longitude);
    }
}

