package com.martkans.solvrojakdojade.services;

import com.martkans.solvrojakdojade.DTOs.StopRestDTO;
import com.martkans.solvrojakdojade.domain.Stop;
import com.martkans.solvrojakdojade.mappers.StopMapper;
import com.martkans.solvrojakdojade.mappers.StopMapperImpl;
import com.martkans.solvrojakdojade.repositories.StopRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class StopServiceImplTest {

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

        verify(stopRepository, times(1)).findAll();
    }
}