package com.spacebot.service;

import com.spacebot.client.SpacexLaunchesClient;
import com.spacebot.dto.SpacexLaunchDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaunchesService {

    private final SpacexLaunchesClient client;

    public LaunchesService(SpacexLaunchesClient client) {
        this.client = client;
    }

    public List<SpacexLaunchDto> getUpcomingLaunches() {
        return client.getUpcomingLaunches();
    }
}