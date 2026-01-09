package com.spacebot.service.impl;

import com.spacebot.client.NewsClient;
import com.spacebot.dto.news.NewsArticleDTO;
import com.spacebot.dto.news.NewsResponseDTO;
import com.spacebot.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NewsServiceImpl implements NewsService {

    private final NewsClient client;

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
        return """
                📰 %s
                🏢 %s
                🗓 %s

                %s

                🔗 %s
                """.formatted(
                article.getTitle(),
                article.getNewsSite(),
                article.getPublishedAt(),
                article.getSummary(),
                article.getUrl()
        );
    }

}
