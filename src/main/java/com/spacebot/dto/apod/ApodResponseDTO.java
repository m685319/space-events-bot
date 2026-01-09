package com.spacebot.dto.apod;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ApodResponseDTO {

    private String title;
    private String date;
    private String explanation;
    private String url;
    @JsonProperty("media_type")
    private String mediaType;

}
