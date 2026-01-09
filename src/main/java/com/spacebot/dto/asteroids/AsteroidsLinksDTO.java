package com.spacebot.dto.asteroids;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AsteroidsLinksDTO {

    private String next;
    private String previous;
    private String self;

}
