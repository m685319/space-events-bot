package com.spacebot.service.impl;

import com.spacebot.client.AsteroidsClient;
import com.spacebot.dto.asteroids.AsteroidsNearEarthObjectsDTO;
import com.spacebot.dto.asteroids.AsteroidsResponseDTO;
import com.spacebot.service.AsteroidsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AsteroidsServiceImpl implements AsteroidsService {

    private final AsteroidsClient client;
    private static final DateTimeFormatter HUMAN_DATE = DateTimeFormatter.ofPattern("MMMM d, yyyy");

    @Override
    public String getUpcomingAsteroids() {
        AsteroidsResponseDTO response = client.getUpcomingAsteroids();
        if (response == null || response.getNearEarthObjects() == null || response.getNearEarthObjects().isEmpty()) {
            return "☄️ You are safe!! No asteroids approaching right now.";
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
        boolean hazardous = neo.getIsPotentiallyHazardousAsteroid();
        String safetyLine = hazardous
                ? "⚠️ This one is classified as *potentially hazardous*. NASA is keeping a close eye on it 👀"
                : "😌 Don’t worry, it’s harmless and will safely miss Earth.";
        return """
                ☄️ %s will fly past Earth on %s
                
                📏 Size: up to %.2f km wide
                🌍 Miss distance: %.1f million km
                
                %s
                
                🔗 %s

                """.formatted(
                neo.getName(),
                LocalDate.parse(neo.getCloseApproachData().getFirst().getCloseApproachDate()).format(HUMAN_DATE),
                neo.getEstimatedDiameter().getKilometers().getEstimatedDiameterMax(),
                Double.parseDouble(neo.getCloseApproachData().getFirst().getMissDistance().getKilometers()) / 1_000_000,
                safetyLine,
                neo.getNasaJplUrl()
        );
    }

}
