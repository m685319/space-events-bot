package com.spacebot.client;

import com.spacebot.dto.apod.ApodResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ApodClient {

    private final RestClient restClient;
    @Value("${apod.url}")
    private String apodUrl;

    public ApodResponseDTO getApod(Optional<LocalDate> date) {
        return restClient.get()
                .uri(apodUrl, uriBuilder -> {
                    date.ifPresent(d -> uriBuilder.queryParam("date", d));
                    return uriBuilder.build();
                })
                .retrieve()
                .body(ApodResponseDTO.class);
    }

}
