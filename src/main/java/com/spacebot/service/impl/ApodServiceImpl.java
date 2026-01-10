package com.spacebot.service.impl;

import com.spacebot.client.ApodClient;
import com.spacebot.dto.apod.ApodResponseDTO;
import com.spacebot.service.ApodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class ApodServiceImpl implements ApodService {

    private final ApodClient client;

    private static final LocalDate FIRST_APOD_DATE = LocalDate.of(1995, 6, 16);

    @Override
    public ApodResponseDTO getTodayApod() {
        ApodResponseDTO response = client.getTodayApod();
        if (response == null) {
            throw new IllegalStateException("APOD response is empty");
        }
        return response;
    }

    @Override
    public ApodResponseDTO getApodByDate(LocalDate date) {
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("APOD is not available for future dates.");
        }
        if (date.isBefore(FIRST_APOD_DATE)) {
            throw new IllegalArgumentException("APOD is available starting from 16.06.1995.");
        }

        ApodResponseDTO response = client.getApodByDate(date);
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
