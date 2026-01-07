package com.spacebot.client;

import com.spacebot.dto.SpacexLaunchDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class SpacexLaunchesClient {

    private static final String URL =
            "https://api.spacexdata.com/v4/launches/upcoming";

    private final RestClient restClient = RestClient.create();

    public List<SpacexLaunchDto> getUpcomingLaunches() {
        SpacexLaunchDto[] launches = restClient.get()
                .uri(URL)
                .retrieve()
                .body(SpacexLaunchDto[].class);

        return List.of(launches);
    }
}