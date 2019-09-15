package com.martkans.solvrojakdojade.services;

import com.martkans.solvrojakdojade.DTOs.PathDTO;
import com.martkans.solvrojakdojade.DTOs.StopRestDTO;
import com.martkans.solvrojakdojade.domain.Stop;
import com.martkans.solvrojakdojade.mappers.StopMapper;
import com.martkans.solvrojakdojade.repositories.StopRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<StopRestDTO> getAllStops() {
        return stopRepository
                .findAll()
                .stream()
                .map(stopMapper::stopToStopRestDto)
                .collect(Collectors.toList());
    }

    @Override
    public PathDTO findPath(Integer sourceId, Integer targetId) {

        Stop source;
        Stop target;
        List<Stop> allStops;
        PathDTO pathDTO = new PathDTO();

        if(stopRepository.findById(sourceId).isPresent()) {
            source = stopRepository.findById(sourceId).get();
        } else {
            source = null;
        }
        //exception handling

        if(stopRepository.findById(targetId).isPresent()) {
            target = stopRepository.findById(targetId).get();
        } else {
            target = null;
        }
        //exception handling

        allStops = new ArrayList<>(stopRepository.findAll());

        pathDTO.getStops().add(stopMapper.stopToStopRestDto(source));
        pathDTO.getStops().add(stopMapper.stopToStopRestDto(target));

        pathDTO.setDistance(40);

        return pathDTO;
    }
}
