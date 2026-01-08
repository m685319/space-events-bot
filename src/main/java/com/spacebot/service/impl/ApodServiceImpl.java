package com.spacebot.service.impl;

import com.spacebot.client.ApodClient;
import com.spacebot.dto.apod.ApodResponseDTO;
import com.spacebot.service.ApodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApodServiceImpl implements ApodService {

    private final ApodClient client;

    @Override
    public ApodResponseDTO getTodayApod() {
        ApodResponseDTO apod = client.getTodayApod();

        if (apod == null) {
            throw new IllegalStateException("APOD response is empty");
        }

        return apod;
    }
}
