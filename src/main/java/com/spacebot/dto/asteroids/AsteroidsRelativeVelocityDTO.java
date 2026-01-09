package com.spacebot.dto.asteroids;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AsteroidsRelativeVelocityDTO {

    @JsonProperty("kilometers_per_second")
    private String kilometersPerSecond;
    @JsonProperty("kilometers_per_hour")
    private String kilometersPerHour;
    @JsonProperty("miles_per_hour")
    private String milesPerHour;

}
