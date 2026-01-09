package com.spacebot.dto.news;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class NewsArticleDTO {

    private String title;
    private String summary;
    private String url;
    @JsonProperty("published_at")
    private String publishedAt;
    @JsonProperty("news_site")
    private String newsSite;

}
