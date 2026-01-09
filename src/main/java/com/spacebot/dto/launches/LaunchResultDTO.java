package com.spacebot.dto.launches;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LaunchResultDTO {

    private Integer id;
    @JsonProperty("cospar_id")
    private String cosparId;
    @JsonProperty("sort_date")
    private String sortDate;
    private String name;
    private LaunchProviderDTO provider;
    private LaunchVehicleDTO vehicle;
    private LaunchPadDTO pad;
    private ArrayList<LaunchMissionDTO> missions;
    @JsonProperty("mission_description")
    private String missionDescription;
    @JsonProperty("launch_description")
    private String launchDescription;
    private String modified;

}
