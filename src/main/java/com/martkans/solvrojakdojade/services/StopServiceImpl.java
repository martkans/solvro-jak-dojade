package com.martkans.solvrojakdojade.services;

import com.martkans.solvrojakdojade.DTOs.DijkstraStopWrapper;
import com.martkans.solvrojakdojade.DTOs.PathDTO;
import com.martkans.solvrojakdojade.DTOs.StopRestDTO;
import com.martkans.solvrojakdojade.domain.Link;
import com.martkans.solvrojakdojade.domain.Stop;
import com.martkans.solvrojakdojade.exceptions.ResourceNotFoundException;
import com.martkans.solvrojakdojade.mappers.StopMapper;
import com.martkans.solvrojakdojade.repositories.StopRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StopServiceImpl implements StopService {

    private static final int STARTING_DISTANCE = 0;

    private final StopRepository stopRepository;
    private final StopMapper stopMapper;

    private Map<Integer, DijkstraStopWrapper> dijkstraStopWrappers = new HashMap<>();
    private Map<Integer, Stop> stops = new HashMap<>();

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

        validData(sourceId, targetId);

        prepareDataForDijkstra(sourceId);

        runDijkstraAlgorithm();

        return buildPath(targetId);
    }

    private void validData (Integer sourceId, Integer targetId){
        if(!stopRepository.findById(sourceId).isPresent())
            throw new ResourceNotFoundException("Stop with id: " + sourceId + " does not exist.");

        if(!stopRepository.findById(targetId).isPresent())
            throw new ResourceNotFoundException("Stop with id: " + targetId + " does not exist.");
    }

    private void prepareDataForDijkstra(Integer sourceId) {

        dijkstraStopWrappers.clear();
        stops.clear();

        stopRepository.findAll().forEach(stop -> stops.put(stop.getId(), stop));

        stops.forEach((id, stop) -> dijkstraStopWrappers.put(id, new DijkstraStopWrapper(stop)));

        dijkstraStopWrappers.get(sourceId).setDistance(STARTING_DISTANCE);

    }

    private void runDijkstraAlgorithm(){

        while (!stops.isEmpty()){
            Optional<DijkstraStopWrapper> minDistStopOptional = dijkstraStopWrappers.values().stream()
                    .filter(wrapper -> stops.containsValue(wrapper.getActualStop()))
                    .min(DijkstraStopWrapper::compareTo);

            if (minDistStopOptional.isPresent()){
                DijkstraStopWrapper minDistStop = minDistStopOptional.get();

                stops.remove(minDistStop.getActualStop().getId());

                for (Link link : minDistStop.getActualStop().getLinks()) {
                    for (Stop stop : link.getStops()) {
                        if(!stop.equals(minDistStop.getActualStop()) && stops.containsValue(stop)){

                            if(dijkstraStopWrappers.get(stop.getId()).getDistance() == null ||
                                    link.getDistance() + minDistStop.getDistance() <
                                            dijkstraStopWrappers.get(stop.getId()).getDistance()){

                                dijkstraStopWrappers.get(stop.getId())
                                        .setDistance(link.getDistance() + minDistStop.getDistance());

                                dijkstraStopWrappers.get(stop.getId())
                                        .setPrecursor(minDistStop.getActualStop());
                            }
                        }
                    }
                }
            }
        }
    }

    private PathDTO buildPath(Integer targetId){

        PathDTO pathDTO = new PathDTO();
        DijkstraStopWrapper actualStopWrapper;

        pathDTO.setDistance(dijkstraStopWrappers
                .get(targetId).getDistance());

        actualStopWrapper = dijkstraStopWrappers.get(targetId);

        do {
            pathDTO.getStops().add(0,
                    stopMapper.stopToStopRestDto(actualStopWrapper.getActualStop()));

            actualStopWrapper = dijkstraStopWrappers.get(
                    actualStopWrapper.getPrecursor().getId());

        } while (actualStopWrapper.getPrecursor() != null);

        pathDTO.getStops().add(0,
                stopMapper.stopToStopRestDto(actualStopWrapper.getActualStop()));

        return pathDTO;
    }

}
