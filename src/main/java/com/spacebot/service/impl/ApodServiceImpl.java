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
        ApodResponseDTO response = client.getTodayApod();
        if (response == null) {
            throw new IllegalStateException("APOD response is empty");
        }
        return response;
    }

    @Override
    public String formatApodMessage(ApodResponseDTO apod) {
        return """
                🪐 %s
                📅 %s

                %s

                🔗 %s
                                
                🔔 Want daily notifications?
                   Use /subscribe_apod to get APOD every day at 5:00 AM UTC.
                """.formatted(
                apod.getTitle(),
                apod.getDate(),
                apod.getExplanation(),
                apod.getUrl()
        );
    }
}
