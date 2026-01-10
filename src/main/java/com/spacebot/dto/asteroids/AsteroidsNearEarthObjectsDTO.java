package com.spacebot.dto.asteroids;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AsteroidsNearEarthObjectsDTO {

    private AsteroidsLinksDTO links;
    private String id;
    @JsonProperty("neo_reference_id")
    private String neoReferenceId;
    private String name;
    @JsonProperty("nasa_jpl_url")
    private String nasaJplUrl;
    @JsonProperty("absolute_magnitude_h")
    private Double absoluteMagnitudeH;
    @JsonProperty("estimated_diameter")
    private AsteroidsEstimatedDiameterDTO estimatedDiameter;
    @JsonProperty("is_potentially_hazardous_asteroid")
    private Boolean isPotentiallyHazardousAsteroid;
    @JsonProperty("close_approach_data")
    private ArrayList<AsteroidsCloseApproachDatumDTO> closeApproachData;
    @JsonProperty("is_sentry_object")
    private Boolean isSentryObject;

}
