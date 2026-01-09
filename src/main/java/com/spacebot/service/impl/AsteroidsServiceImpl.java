package com.spacebot.service.impl;

import com.spacebot.client.AsteroidsClient;
import com.spacebot.dto.asteroids.AsteroidsNearEarthObjectsDTO;
import com.spacebot.dto.asteroids.AsteroidsResponseDTO;
import com.spacebot.service.AsteroidsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AsteroidsServiceImpl implements AsteroidsService {

    private final AsteroidsClient client;

    @Override
    public String getUpcomingAsteroids() {
        AsteroidsResponseDTO response = client.getUpcomingAsteroids();
        if (response == null || response.getNearEarthObjects() == null || response.getNearEarthObjects().isEmpty()) {
            return "☄️ No asteroids coming right now.";
        }
        return response.getNearEarthObjects()
                .values()
                .stream()
                .flatMap(List::stream)
                .map(this::formatInformation)
                .limit(5)
                .collect(Collectors.joining("\n"));
    }

    private String formatInformation(AsteroidsNearEarthObjectsDTO neo) {
        return """
                ☄️ %s is approaching you on %s
                """.formatted(
                neo.getName(),
                neo.getCloseApproachData().getFirst().getCloseApproachDate()
        );
    }

}
