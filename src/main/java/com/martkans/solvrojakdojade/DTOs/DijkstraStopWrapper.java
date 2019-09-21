package com.martkans.solvrojakdojade.DTOs;

import com.martkans.solvrojakdojade.domain.Stop;
import lombok.Data;

@Data
public class DijkstraStopWrapper {

    private Integer distance = null;
    private Stop precursor = null;
    private Stop actualStop;

    public DijkstraStopWrapper(Stop actualStop) {
        this.actualStop = actualStop;
    }
}
