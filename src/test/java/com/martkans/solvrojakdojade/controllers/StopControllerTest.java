package com.martkans.solvrojakdojade.controllers;

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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StopControllerTest {

    @Mock
    private StopService stopService;

    private MockMvc mockMvc;
    private StopController stopController;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        stopController = new StopController(stopService);

        mockMvc = MockMvcBuilders
                    .standaloneSetup(stopController)
                    .build();
    }

    @Test
    public void getAllStops() throws Exception {

        StopRestDTO stopDTO1 = new StopRestDTO();
        StopRestDTO stopDTO2 = new StopRestDTO();

        List<StopRestDTO> stopDTOs = Arrays.asList(stopDTO1, stopDTO2);

        when(stopService.getAllStops())
                .thenReturn(stopDTOs);

        mockMvc.perform(MockMvcRequestBuilders.get("/stops")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        verify(stopService, times(1)).getAllStops();
    }
}