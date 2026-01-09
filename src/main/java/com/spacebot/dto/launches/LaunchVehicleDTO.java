package com.spacebot.dto.launches;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LaunchVehicleDTO {

    private Integer id;
    private String name;
    @JsonProperty("company_id")
    private Integer companyId;

}
