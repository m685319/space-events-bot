package com.spacebot.dto.asteroids;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AsteroidsResponseDTO {

        @JsonProperty("links")
        private AsteroidsLinksDTO links;
        @JsonProperty("element_count")
        private Integer elementCount;
        @JsonProperty("near_earth_objects")
        private Map<LocalDate, List<AsteroidsNearEarthObjectsDTO>> nearEarthObjects;

}
