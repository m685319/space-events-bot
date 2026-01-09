package com.spacebot.dto.asteroids;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AsteroidsMissDistanceDTO {

    private String astronomical;
    private String lunar;
    private String kilometers;
    private String miles;

}
