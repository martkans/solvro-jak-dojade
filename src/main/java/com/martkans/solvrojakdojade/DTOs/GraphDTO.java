package com.martkans.solvrojakdojade.DTOs;


import com.martkans.solvrojakdojade.domain.Link;
import com.martkans.solvrojakdojade.domain.Stop;

import java.util.List;

public class GraphDTO {

    private Boolean directed;
    private Object graph;
    private List<Link> links;
    private Boolean multigraph;
    private List<Stop> nodes;
}
