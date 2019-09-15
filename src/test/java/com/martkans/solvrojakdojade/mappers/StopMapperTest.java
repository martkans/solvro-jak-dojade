package com.martkans.solvrojakdojade.mappers;

import com.martkans.solvrojakdojade.DTOs.StopDTO;
import com.martkans.solvrojakdojade.DTOs.StopRestDTO;
import com.martkans.solvrojakdojade.domain.Stop;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StopMapperTest {

    private static final String BUS_STOP = "Bus stop";
    private static final Integer ID = 1;

    private Stop stop;
    private StopMapper stopMapper = StopMapper.INSTANCE;

    @Test
    public void stopDtoToStop() {

        StopDTO stopDTO = new StopDTO();
        stopDTO.setId(ID);
        stopDTO.setStopName(BUS_STOP);


        stop = stopMapper.stopDtoToStop(stopDTO);

        assertEquals(ID, stop.getId());
        assertEquals(BUS_STOP, stop.getStopName());

    }

    @Test
    public void stopToStopRestDto(){
        stop = new Stop();
        stop.setId(ID);
        stop.setStopName(BUS_STOP);

        StopRestDTO stopRestDTO = stopMapper.stopToStopRestDto(stop);

        assertEquals(BUS_STOP, stopRestDTO.getName());
    }
}