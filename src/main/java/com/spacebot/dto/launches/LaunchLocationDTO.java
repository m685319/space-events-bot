package com.spacebot.dto.launches;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LaunchLocationDTO {

    private Integer id;
    private String name;
    private String state;
    @JsonProperty("statename")
    private String stateName;
    private String country;

}
