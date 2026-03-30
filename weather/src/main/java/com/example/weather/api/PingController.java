package com.example.weather.api;

import com.example.weather.api.dto.PingResponse;
import java.sql.Connection;
import java.time.Instant;
import javax.sql.DataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PingController {

    private final DataSource dataSource;

    public PingController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/ping")
    public PingResponse ping() {
        boolean dbUp = false;
        try (Connection connection = dataSource.getConnection()) {
            dbUp = connection.isValid(1);
        } catch (Exception ignored) {
            dbUp = false;
        }

        return new PingResponse(dbUp ? "UP" : "DEGRADED", "weather", Instant.now());
    }
}
