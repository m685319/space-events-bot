package com.spacebot.client;

import com.spacebot.dto.launches.LaunchResponseDTO;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
@Component
public class LaunchesClient {

    private final RestClient restClient;
    @Value("${launches.url}")
    private String launchesUrl;

    public LaunchResponseDTO getUpcomingLaunches() {
        try {
            return restClient.get()
                    .uri(launchesUrl)
                    .retrieve()
                    .body(LaunchResponseDTO.class);
        } catch (Exception exception) {
            log.error("Error fetching launches: {}", exception.getMessage());
            return null;
        }
    }

}
