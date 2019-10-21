package com.martkans.solvrojakdojade.restapp.services;

import com.martkans.solvrojakdojade.exceptions.ResourceNotFoundException;
import com.martkans.solvrojakdojade.restapp.DTOs.PathDTO;
import com.martkans.solvrojakdojade.restapp.DTOs.StopRestDTO;
import com.martkans.solvrojakdojade.restapp.domain.Link;
import com.martkans.solvrojakdojade.restapp.domain.Stop;
import com.martkans.solvrojakdojade.restapp.mappers.StopMapper;
import com.martkans.solvrojakdojade.restapp.mappers.StopMapperImpl;
import com.martkans.solvrojakdojade.restapp.repositories.StopRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class StopServiceImplTest {

    private static final int WANTED_NUMBER_OF_INVOCATIONS = 1;

    @Mock
    private StopRepository stopRepository;

    private final StopMapper stopMapper = new StopMapperImpl();
    private StopService stopService;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        stopService = new StopServiceImpl(stopRepository, stopMapper);
    }

    @Test
    public void getAllStops() {

        Stop stop1 = new Stop();
        Stop stop2 = new Stop();

        List<Stop> stops = new ArrayList<>();
        stops.add(stop1);
        stops.add(stop2);

        when(stopRepository.findAll())
                .thenReturn(stops);

        List<StopRestDTO> stopDTOS = stopService.getAllStops();

        assertEquals(stops.size(), stopDTOS.size());

        verify(stopRepository, times(WANTED_NUMBER_OF_INVOCATIONS)).findAll();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findPathInvalidData() throws ResourceNotFoundException {

        when(stopRepository.findById(anyInt()))
                .thenReturn(Optional.empty());

        stopService.findPath(1, 3);
    }

    @Test
    public void findPathValidData() {
        Stop stop1 = new Stop();
        stop1.setId(1);
        stop1.setStopName("Stop1");

        Stop stop2 = new Stop();
        stop2.setId(2);
        stop2.setStopName("Stop2");

        Stop stop3 = new Stop();
        stop3.setId(3);
        stop3.setStopName("Stop3");

        Stop stop4 = new Stop();
        stop4.setId(4);
        stop4.setStopName("Stop4");

        Link link12 = new Link();
        link12.setId(1);
        link12.setDistance(1);

        Link link13 = new Link();
        link13.setId(2);
        link13.setDistance(4);

        Link link14 = new Link();
        link14.setId(3);
        link14.setDistance(3);

        Link link24 = new Link();
        link24.setId(4);
        link24.setDistance(1);

        Link link34 = new Link();
        link34.setId(5);
        link34.setDistance(1);

        stop1.addLink(link12);
        stop1.addLink(link13);
        stop1.addLink(link14);

        stop2.addLink(link12);
        stop2.addLink(link24);

        stop3.addLink(link13);
        stop3.addLink(link34);

        stop4.addLink(link14);
        stop4.addLink(link24);
        stop4.addLink(link34);

        when(stopRepository.findById(1))
                .thenReturn(Optional.of(stop1));
        when(stopRepository.findById(3))
                .thenReturn(Optional.of(stop3));
        when(stopRepository.findAll())
                .thenReturn(Arrays.asList(stop1, stop2, stop3, stop4));

        PathDTO pathDTO = stopService.findPath(1, 3);

        assertEquals(new Integer(3), pathDTO.getDistance());
        assertEquals(4, pathDTO.getStops().size());
    }
}