package com.spacebot.service.impl;

import com.spacebot.client.LaunchesClient;
import com.spacebot.dto.LaunchDTO;
import com.spacebot.service.LaunchesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class LaunchesServiceImpl implements LaunchesService {

    private final LaunchesClient client;

    @Override
    public String getUpcomingLaunches() {
        LaunchDTO launchDTO = client.getUpcomingLaunches();
        log.info(launchDTO.toString());
        String result = launchDTO.getResult().stream()
                .map(launchResult -> "• " + launchResult.getLaunchDescription())
                .collect(Collectors.joining("\n"));
        return result;
    }

}
