package com.spacebot.dto.apod;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ApodResponseDTO {

    private String title;
    private String date;
    private String explanation;
    private String url;
    private String mediaType;

}
