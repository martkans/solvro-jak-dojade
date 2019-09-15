package com.martkans.solvrojakdojade.DTOs;

import com.martkans.solvrojakdojade.domain.Stop;
import lombok.Data;

@Data
public class DijkstraStopWrapper {

    private Integer distance = 0;
    private Stop precursor = null;
    private Stop actualStop;
}
