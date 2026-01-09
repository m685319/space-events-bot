package com.spacebot.client;

import com.spacebot.dto.news.NewsResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
@Component
public class NewsClient {

    private final RestClient restClient;

    public NewsResponseDTO getLatestNews() {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("api.spaceflightnewsapi.net")
                        .path("/v4/articles/")
                        .queryParam("limit", 5)
                        .queryParam("ordering", "-published_at")
                        .build())
                .retrieve()
                .body(NewsResponseDTO.class);
    }
}
