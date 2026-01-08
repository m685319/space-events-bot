package com.spacebot.client;

import com.spacebot.dto.launches.LaunchDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
@Component
public class LaunchesClient {

    private final RestClient restClient;
    @Value("${launches.url}")
    private String LAUNCHES_URL;

    public LaunchDTO getUpcomingLaunches() {
        LaunchDTO launchDTO = restClient.get()
                .uri(LAUNCHES_URL)
                .retrieve()
                .body(LaunchDTO.class);
        return launchDTO;
    }

}
