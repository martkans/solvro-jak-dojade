package com.martkans.solvrojakdojade.DTOs;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PathDTO {

    private List<StopRestDTO> stops = new ArrayList<>();
    private Integer distance;
}
