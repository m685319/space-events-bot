package com.spacebot.service.impl;

import com.spacebot.client.LaunchesClient;
import com.spacebot.dto.launches.LaunchResponseDTO;
import com.spacebot.dto.launches.LaunchResultDTO;
import com.spacebot.service.LaunchesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LaunchesServiceImpl implements LaunchesService {

    private final LaunchesClient client;

    @Override
    public String getUpcomingLaunches() {
        LaunchResponseDTO response = client.getUpcomingLaunches();
        if (response == null || response.getResult() == null || response.getResult().isEmpty()) {
            return "🚀 No upcoming launches available right now.";
        }
        return response.getResult()
                .stream()
                .map(this::formatInformation)
                .collect(Collectors.joining("\n"));
    }

    private String formatInformation(LaunchResultDTO launchResult) {
        return """
                🚀 %s
                """.formatted(
                launchResult.getLaunchDescription()
                );
    }

}
