package com.spacebot.client;

import com.spacebot.dto.news.NewsResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
@Component
public class NewsClient {

    private final RestClient restClient;
    @Value("${news.url}")
    private String newsUrl;

    public NewsResponseDTO getLatestNews() {
        try {
            return restClient.get()
                    .uri(newsUrl, uriBuilder -> uriBuilder
                            .queryParam("limit", 5)
                            .queryParam("ordering", "-published_at")
                            .build())
                    .retrieve()
                    .body(NewsResponseDTO.class);
        } catch (Exception exception) {
            log.error("Error fetching news: {}", exception.getMessage());
            return null;
        }
    }

}
