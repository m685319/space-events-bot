package com.spacebot.dto.launches;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LaunchPadDTO {

    private Integer id;
    private String name;
    private LaunchLocationDTO location;

}
