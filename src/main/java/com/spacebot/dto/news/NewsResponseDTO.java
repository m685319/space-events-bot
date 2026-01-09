package com.spacebot.dto.news;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class NewsResponseDTO {

    private Integer count;
    private List<NewsArticleDTO> results;

}
