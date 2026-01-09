package com.spacebot.dto.asteroids;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AsteroidsEstimatedDiameterDTO {

    private AsteroidsKilometersDTO kilometers;
    private AsteroidsMetersDTO meters;
    private AsteroidsMilesDTO miles;
    private AsteroidsFeetDTO feet;

}
