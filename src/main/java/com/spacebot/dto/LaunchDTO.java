package com.spacebot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LaunchDTO {

    @JsonProperty("count")
    private Integer count;
    @JsonProperty("limit")
    private Integer limit;
    @JsonProperty("total")
    private Integer total;
    @JsonProperty("last_page")
    private Integer lastPage;
    @JsonProperty("result")
    private ArrayList<LaunchResultDTO> result;

}