package com.spacebot.client;

import com.spacebot.dto.asteroids.AsteroidsResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
@Component
public class AsteroidsClient {

    private final RestClient restClient;
    @Value("${asteroids.url}")
    private String asteroidsUrl;
    @Value("${asteroids.apiKey}")
    private String asteroidsApiKey;

    public AsteroidsResponseDTO getUpcomingAsteroids() {
        try {
            return restClient.get()
                    .uri(asteroidsUrl, uriBuilder -> uriBuilder
                            .queryParam("api_key", asteroidsApiKey)
                            .build())
                    .retrieve()
                    .body(AsteroidsResponseDTO.class);
        } catch (Exception exception) {
            log.error("Error fetching asteroids: {}", exception.getMessage());
            return null;
        }
    }

}
