package com.martkans.solvrojakdojade.restapp.mappers;

import com.martkans.solvrojakdojade.restapp.DTOs.StopDTO;
import com.martkans.solvrojakdojade.restapp.DTOs.StopRestDTO;
import com.martkans.solvrojakdojade.restapp.domain.Stop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StopMapper {

    StopMapper INSTANCE = Mappers.getMapper(StopMapper.class);

    @Mapping(target = "links", ignore = true)
    Stop stopDtoToStop(StopDTO stopDTO);

    @Mapping(source = "stopName", target = "name")
    StopRestDTO stopToStopRestDto(Stop stop);
}
