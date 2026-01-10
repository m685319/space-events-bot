package com.spacebot.client;

import com.spacebot.dto.apod.ApodResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class ApodClient {

    private final RestClient restClient;
    @Value("${apod.url}")
    private String apodUrl;

    public ApodResponseDTO getTodayApod() {
        return restClient.get()
                .uri(apodUrl)
                .retrieve()
                .body(ApodResponseDTO.class);
    }

    public ApodResponseDTO getApodByDate(LocalDate date) {
        return restClient.get()
                .uri(apodUrl, uriBuilder -> uriBuilder
                        .queryParam("date", date)
                        .build())
                .retrieve()
                .body(ApodResponseDTO.class);
    }

}
