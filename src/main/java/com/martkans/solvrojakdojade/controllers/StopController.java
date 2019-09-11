package com.martkans.solvrojakdojade.controllers;

import com.martkans.solvrojakdojade.DTOs.StopDTO;
import com.martkans.solvrojakdojade.services.StopService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StopController {

    private final StopService stopService;

    public StopController(StopService stopService) {
        this.stopService = stopService;
    }

    @GetMapping("stops")
    @ResponseStatus(HttpStatus.OK)
    public List<StopDTO> getAllStops(){
        return stopService.getAllStops();
    }
}
