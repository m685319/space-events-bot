package com.spacebot.dto.launches;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LaunchResponseDTO {

    private Integer count;
    private Integer limit;
    private Integer total;
    @JsonProperty("last_page")
    private Integer lastPage;
    private ArrayList<LaunchResultDTO> result;

}