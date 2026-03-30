package com.example.weather.service;

import com.example.weather.api.dto.ObservationCreateRequest;
import com.example.weather.api.dto.ObservationResponse;
import com.example.weather.db.WeatherObservation;
import com.example.weather.db.WeatherObservationRepository;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ObservationService {

    private final WeatherObservationRepository repository;

    public ObservationService(WeatherObservationRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ObservationResponse create(ObservationCreateRequest request) {
        WeatherObservation observation = new WeatherObservation();
        observation.setCity(request.city().trim());
        observation.setLatitude(request.latitude());
        observation.setLongitude(request.longitude());
        observation.setTemperature(request.temperature());
        observation.setHumidity(request.humidity());
        observation.setWindSpeed(request.windSpeed());
        observation.setPressure(request.pressure());
        observation.setWeatherCode(request.weatherCode());
        observation.setDescription(request.description());
        observation.setRecordedAt(request.recordedAt());
        observation.setSource(request.source());

        WeatherObservation saved = repository.save(observation);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<ObservationResponse> list(int limit) {
        int pageSize = Math.min(Math.max(limit, 1), 100);
        return repository
                .findAllByOrderByCreatedAtDesc(PageRequest.of(0, pageSize))
                .map(ObservationService::toResponse)
                .toList();
    }

    private static ObservationResponse toResponse(WeatherObservation observation) {
        return new ObservationResponse(
                observation.getId() == null ? 0L : observation.getId(),
                observation.getCity(),
                observation.getLatitude(),
                observation.getLongitude(),
                observation.getTemperature(),
                observation.getHumidity(),
                observation.getWindSpeed(),
                observation.getPressure(),
                observation.getWeatherCode(),
                observation.getDescription(),
                observation.getRecordedAt(),
                observation.getSource(),
                observation.getCreatedAt());
    }
}
