package com.spacebot.client;

import com.spacebot.dto.launches.LaunchResponseDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
@Component
public class LaunchesClient {

    private final RestClient restClient;
    @Value("${launches.url}")
    private String launchesUrl;

    public LaunchResponseDTO getUpcomingLaunches() {
        return restClient.get()
                .uri(launchesUrl)
                .retrieve()
                .body(LaunchResponseDTO.class);
    }

}
