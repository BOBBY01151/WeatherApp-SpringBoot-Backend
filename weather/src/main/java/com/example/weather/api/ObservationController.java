package com.example.weather.api;

import com.example.weather.api.dto.ObservationCreateRequest;
import com.example.weather.api.dto.ObservationResponse;
import com.example.weather.service.ObservationService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/observations")
public class ObservationController {

    private final ObservationService observationService;

    public ObservationController(ObservationService observationService) {
        this.observationService = observationService;
    }

    @PostMapping
    public ObservationResponse create(@Valid @RequestBody ObservationCreateRequest request) {
        return observationService.create(request);
    }

    @GetMapping
    public List<ObservationResponse> list(@RequestParam(defaultValue = "20") int limit) {
        return observationService.list(limit);
    }
}

