package com.martkans.solvrojakdojade.restapp.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StopDTO {

    private Integer id;

    @JsonProperty("stop_name")
    private String stopName;
}
