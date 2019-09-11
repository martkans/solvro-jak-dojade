package com.martkans.solvrojakdojade.services;

import com.martkans.solvrojakdojade.DTOs.StopDTO;
import com.martkans.solvrojakdojade.mappers.StopMapper;
import com.martkans.solvrojakdojade.repositories.StopRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StopServiceImpl implements StopService {

    private final StopRepository stopRepository;
    private final StopMapper stopMapper;

    public StopServiceImpl(StopRepository stopRepository, StopMapper stopMapper) {
        this.stopRepository = stopRepository;
        this.stopMapper = stopMapper;
    }

    @Override
    public List<StopDTO> getAllStops() {
        return stopRepository
                .findAll()
                .stream()
                .map(stopMapper::stopToStopDto)
                .collect(Collectors.toList());
    }
}
