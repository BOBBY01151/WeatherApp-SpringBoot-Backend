package com.example.weather.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherObservationRepository extends JpaRepository<WeatherObservation, Long> {

    Page<WeatherObservation> findAllByOrderByCreatedAtDesc(Pageable pageable);
}

