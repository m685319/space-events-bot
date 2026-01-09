package com.spacebot.dto.asteroids;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AsteroidsCloseApproachDatumDTO {

    @JsonProperty("close_approach_date")
    private String closeApproachDate;
    @JsonProperty("close_approach_date_full")
    private String closeApproachDateFull;
    @JsonProperty("epoch_date_close_approach")
    private Object epochDateCloseApproach;
    @JsonProperty("relative_velocity")
    private AsteroidsRelativeVelocityDTO relativeVelocity;
    @JsonProperty("miss_distance")
    private AsteroidsMissDistanceDTO missDistance;
    @JsonProperty("orbiting_body")
    private String orbitingBody;

}
