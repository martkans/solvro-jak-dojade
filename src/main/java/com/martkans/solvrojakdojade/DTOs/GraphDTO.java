package com.martkans.solvrojakdojade.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class GraphDTO {

    private Boolean directed;
    private Object graph;
    private List<LinkDTO> links;
    private Boolean multigraph;
    private List<StopDTO> nodes;
}
