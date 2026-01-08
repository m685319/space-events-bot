package com.spacebot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LaunchProviderDTO {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;

}
