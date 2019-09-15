package com.martkans.solvrojakdojade.controllers;

import com.martkans.solvrojakdojade.DTOs.PathDTO;
import com.martkans.solvrojakdojade.DTOs.StopRestDTO;
import com.martkans.solvrojakdojade.services.StopService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public List<StopRestDTO> getAllStops(){
        return stopService.getAllStops();
    }

    @GetMapping("path")
    @ResponseStatus(HttpStatus.OK)
    public PathDTO getPathBetweenStops(@RequestParam String source, @RequestParam String target){
        return stopService.findPath(Integer.valueOf(source), Integer.valueOf(target));
    }
}
