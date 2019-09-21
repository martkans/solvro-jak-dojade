package com.martkans.solvrojakdojade.services;

import com.martkans.solvrojakdojade.DTOs.DijkstraStopWrapper;
import com.martkans.solvrojakdojade.DTOs.PathDTO;
import com.martkans.solvrojakdojade.DTOs.StopRestDTO;
import com.martkans.solvrojakdojade.domain.Link;
import com.martkans.solvrojakdojade.domain.Stop;
import com.martkans.solvrojakdojade.mappers.StopMapper;
import com.martkans.solvrojakdojade.repositories.StopRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

        PathDTO pathDTO = new PathDTO();
        List<DijkstraStopWrapper> dijkstraStopWrappers = new ArrayList<>();
        List<Stop> stops = stopRepository.findAll();
        DijkstraStopWrapper actualStopWrapper = null;

        stops.forEach(stop -> dijkstraStopWrappers.add(new DijkstraStopWrapper(stop)));

        dijkstraStopWrappers.get(getIndexInArrayWrapper(sourceId, dijkstraStopWrappers)).setDistance(0);

        while (!stops.isEmpty()){
            Optional<DijkstraStopWrapper> minDistStopOptional = dijkstraStopWrappers.stream()
                    .filter(wrapper -> stops.contains(wrapper.getActualStop()))
                    .min((d1, d2) -> {
                        if (d1.getDistance() == null && d2.getDistance() == null){
                            return 0;
                        } else if (d1.getDistance() == null) {
                            return 1;
                        } else if (d2.getDistance() == null){
                            return -1;
                        } else if(d1.getDistance().equals(d2.getDistance())) {
                            return 0;
                        } else if (d1.getDistance() < d2.getDistance()) {
                            return -1;
                        } else {
                            return 1;
                        }
                    });

            if (minDistStopOptional.isPresent()){
                    DijkstraStopWrapper minDistStop = minDistStopOptional.get();

                    stops.remove(getIndexInArrayStop(minDistStop.getActualStop().getId(), stops).intValue());

                for (Link link : minDistStop.getActualStop().getLinks()) {
                    for (Stop stop : link.getStops()) {
                        if(!stop.equals(minDistStop.getActualStop()) && stops.contains(stop)){
                            int indexOfStop = getIndexInArrayWrapper(stop.getId(), dijkstraStopWrappers);
                            if(dijkstraStopWrappers.get(indexOfStop).getDistance() == null ||
                                    link.getDistance() + minDistStop.getDistance() <
                                        dijkstraStopWrappers.get(indexOfStop).getDistance()){

                                dijkstraStopWrappers.get(indexOfStop)
                                        .setDistance(link.getDistance() + minDistStop.getDistance());

                                dijkstraStopWrappers.get(indexOfStop)
                                        .setPrecursor(minDistStop.getActualStop());
                            }
                        }
                    }
                }
            }
        }

        pathDTO.setDistance(dijkstraStopWrappers
                .get(getIndexInArrayWrapper(targetId, dijkstraStopWrappers)).getDistance());

        actualStopWrapper = dijkstraStopWrappers.get(
                                    getIndexInArrayWrapper(targetId, dijkstraStopWrappers));

        do {
            pathDTO.getStops().add(0,
                    stopMapper.stopToStopRestDto(actualStopWrapper.getActualStop()));

            actualStopWrapper = dijkstraStopWrappers.get(
                    getIndexInArrayWrapper(actualStopWrapper.getPrecursor().getId(), dijkstraStopWrappers));

        } while (actualStopWrapper.getPrecursor() != null);

        pathDTO.getStops().add(0, stopMapper.stopToStopRestDto(actualStopWrapper.getActualStop()));

        return pathDTO;
    }

    private Integer getIndexInArrayWrapper(Integer id, List<DijkstraStopWrapper> dijkstraStopWrappers) {
        for (int i = 0; i < dijkstraStopWrappers.size(); i++){
            if (id.equals(dijkstraStopWrappers.get(i).getActualStop().getId()))
                return i;
        }

        return -1;
    }

    private Integer getIndexInArrayStop(Integer id, List<Stop> stops) {
        for (int i = 0; i < stops.size(); i++){
            if (id.equals(stops.get(i).getId()))
                return i;
        }

        return -1;
    }
}
