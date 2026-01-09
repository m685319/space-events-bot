package com.spacebot.client;

import com.spacebot.dto.asteroids.AsteroidsResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
@Component
public class AsteroidsClient {

    private final RestClient restClient;
    @Value("${asteroids.url}")
    private String asteroidsUrl;
    @Value("${asteroids.apiKey}")
    private String asteroidsApiKey;

    public AsteroidsResponseDTO getUpcomingAsteroids() {
        return restClient.get()
                .uri(asteroidsUrl, uriBuilder -> uriBuilder
                        .queryParam("api_key", asteroidsApiKey)
                        .build())
                .retrieve()
                .body(AsteroidsResponseDTO.class);
    }

}
