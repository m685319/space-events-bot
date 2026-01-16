package com.spacebot.client;

import com.spacebot.dto.apod.ApodResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
@Component
public class ApodClient {

    private final RestClient restClient;
    @Value("${apod.url}")
    private String apodUrl;

    public ApodResponseDTO getApod(LocalDate date) {
        try {
            return restClient.get()
                    .uri(apodUrl, uriBuilder -> uriBuilder
                            .queryParam("date", date)
                            .build())
                    .retrieve()
                    .body(ApodResponseDTO.class);
        } catch (Exception exception) {
            log.error("Error fetching APOD for date {}: {}", date, exception.getMessage());
            return null;
        }
    }

}
