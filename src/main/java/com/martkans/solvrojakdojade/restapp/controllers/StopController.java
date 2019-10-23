package com.martkans.solvrojakdojade.restapp.controllers;

import com.martkans.solvrojakdojade.restapp.DTOs.PathDTO;
import com.martkans.solvrojakdojade.restapp.DTOs.StopRestDTO;
import com.martkans.solvrojakdojade.restapp.services.StopService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@RestController
public class StopController {

    private final StopService stopService;

    public StopController(StopService stopService) {
        this.stopService = stopService;
    }

    @GetMapping("stops")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Returns list of stops in Solvro City.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "JSON array of checklists' names.")
    })
    public List<StopRestDTO> getAllStops(){
        return stopService.getAllStops();
    }

    @GetMapping("path")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Returns list of stops in path and total distance.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "source", required = true, value = "Stop where the path begins", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "target", required = true, value = "Stop where the path ends", dataType = "string", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "JSON containing stops and total distance.")
    })
    public PathDTO getPathBetweenStops(@RequestParam String source, @RequestParam String target){
        return stopService.findPath(Integer.valueOf(source), Integer.valueOf(target));
    }
}
