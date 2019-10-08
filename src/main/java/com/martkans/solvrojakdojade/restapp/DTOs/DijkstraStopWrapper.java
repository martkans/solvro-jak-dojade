package com.martkans.solvrojakdojade.restapp.DTOs;

import com.martkans.solvrojakdojade.restapp.domain.Stop;
import lombok.Data;


@Data
public class DijkstraStopWrapper implements Comparable<DijkstraStopWrapper> {

    private static final int EQUAL = 0;
    private static final int GREATER = 1;
    private static final int LESS = -1;

    private Integer distance = null;
    private Stop precursor = null;
    private Stop actualStop;

    public DijkstraStopWrapper(Stop actualStop) {
        this.actualStop = actualStop;
    }

    @Override
    public int compareTo(DijkstraStopWrapper otherObject) {
        if (this.getDistance() == null && otherObject.getDistance() == null){
            return EQUAL;
        } else if (this.getDistance() == null) {
            return GREATER;
        } else if (otherObject.getDistance() == null){
            return LESS;
        } else if(this.getDistance().equals(otherObject.getDistance())) {
            return EQUAL;
        } else if (this.getDistance() < otherObject.getDistance()) {
            return LESS;
        } else {
            return GREATER;
        }
    }
}
