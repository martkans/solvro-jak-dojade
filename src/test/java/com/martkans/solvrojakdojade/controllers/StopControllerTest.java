package com.martkans.solvrojakdojade.controllers;

import com.martkans.solvrojakdojade.DTOs.PathDTO;
import com.martkans.solvrojakdojade.DTOs.StopRestDTO;
import com.martkans.solvrojakdojade.services.StopService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StopControllerTest {

    private static final int DISTANCE = 10;
    private static final int WANTED_NUMBER_OF_INVOCATIONS = 1;
    private static final int SIZE = 3;


    @Mock
    private StopService stopService;

    private MockMvc mockMvc;
    private List<StopRestDTO> stopDTOs;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        StopController stopController = new StopController(stopService);

        mockMvc = MockMvcBuilders
                    .standaloneSetup(stopController)
                    .build();

        StopRestDTO stopDTO1 = new StopRestDTO();
        StopRestDTO stopDTO2 = new StopRestDTO();
        StopRestDTO stopDTO3 = new StopRestDTO();

        stopDTOs = Arrays.asList(stopDTO1, stopDTO2, stopDTO3);
    }

    @Test
    public void getAllStops() throws Exception {

        when(stopService.getAllStops())
                .thenReturn(stopDTOs);

        mockMvc.perform(MockMvcRequestBuilders.get("/stops")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(SIZE)));

        verify(stopService, times(WANTED_NUMBER_OF_INVOCATIONS)).getAllStops();
    }

    @Test
    public void getPathBetweenStops() throws Exception {

        PathDTO pathDTO = new PathDTO();
        pathDTO.setDistance(DISTANCE);
        pathDTO.setStops(stopDTOs);

        when(stopService.findPath(anyInt(), anyInt()))
                .thenReturn(pathDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/path?source=0&target=3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stops", hasSize(SIZE)))
                .andExpect(jsonPath("$.distance", is(DISTANCE)));

        verify(stopService, times(WANTED_NUMBER_OF_INVOCATIONS)).findPath(anyInt(), anyInt());
    }
}