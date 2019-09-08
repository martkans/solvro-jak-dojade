package com.martkans.solvrojakdojade.mappers;

import com.martkans.solvrojakdojade.DTOs.StopDTO;
import com.martkans.solvrojakdojade.domain.Stop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StopMapper {

    StopMapper INSTANCE = Mappers.getMapper(StopMapper.class);

    @Mapping(target = "targetLinks", ignore = true)
    @Mapping(target = "sourceLinks", ignore = true)
    Stop stopDtoToStop(StopDTO stopDTO);
}
