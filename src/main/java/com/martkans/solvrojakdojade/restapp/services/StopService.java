package com.martkans.solvrojakdojade.restapp.services;

import com.martkans.solvrojakdojade.restapp.DTOs.PathDTO;
import com.martkans.solvrojakdojade.restapp.DTOs.StopRestDTO;

import java.util.List;

public interface StopService {

    List<StopRestDTO> getAllStops();
    PathDTO findPath(Integer sourceId, Integer targetId);
}
