package com.spacebot.dto.asteroids;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AsteroidsKilometersDTO {

    @JsonProperty("estimated_diameter_min")
    private Double estimatedDiameterMin;
    @JsonProperty("estimated_diameter_max")
    private Double estimatedDiameterMax;

}
