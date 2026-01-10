package com.spacebot.service.impl;

import com.spacebot.client.NewsClient;
import com.spacebot.dto.news.NewsArticleDTO;
import com.spacebot.dto.news.NewsResponseDTO;
import com.spacebot.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NewsServiceImpl implements NewsService {

    private final NewsClient client;
    private static final DateTimeFormatter HUMAN_DATE_TIME = DateTimeFormatter.ofPattern("MMMM d, yyyy 'at' hh:mm:ss a 'UTC'");

    @Override
    public String getLatestNews() {
        NewsResponseDTO response = client.getLatestNews();
        if (response == null || response.getResults() == null || response.getResults().isEmpty()) {
            return "📰 No space news available right now.";
        }
        return response.getResults()
                .stream()
                .map(this::formatArticle)
                .collect(Collectors.joining("\n\n"));
    }

    private String formatArticle(NewsArticleDTO article) {
        Instant instant = Instant.parse(article.getPublishedAt());
        ZonedDateTime dateTime = instant.atZone(ZoneOffset.UTC);
        return """
                📰 %s
                🏢 %s
                📅 %s

                %s

                🔗 %s
                """.formatted(
                article.getTitle(),
                article.getNewsSite(),
                dateTime.format(HUMAN_DATE_TIME),
                article.getSummary(),
                article.getUrl()
        );
    }

}
