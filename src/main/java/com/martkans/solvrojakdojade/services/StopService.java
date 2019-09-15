package com.martkans.solvrojakdojade.services;

import com.martkans.solvrojakdojade.DTOs.PathDTO;
import com.martkans.solvrojakdojade.DTOs.StopRestDTO;

import java.util.List;

public interface StopService {

    List<StopRestDTO> getAllStops();
    PathDTO findPath(Integer sourceId, Integer targetId);
}
