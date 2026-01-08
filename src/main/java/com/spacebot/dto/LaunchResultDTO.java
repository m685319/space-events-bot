package com.spacebot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LaunchResultDTO {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("cospar_id")
    private String cosparId;
    @JsonProperty("sort_date")
    private String sortDate;
    @JsonProperty("name")
    private String name;
    @JsonProperty("provider")
    private LaunchProviderDTO provider;
    @JsonProperty("vehicle")
    private LaunchVehicleDTO vehicle;
    @JsonProperty("pad")
    private LaunchPadDTO pad;
    @JsonProperty("missions")
    private ArrayList<LaunchMissionDTO> missions;
    @JsonProperty("mission_description")
    private String missionDescription;
    @JsonProperty("launch_description")
    private String launchDescription;
    @JsonProperty("modified")
    private String modified;

}
