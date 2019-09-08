package com.martkans.solvrojakdojade.mappers;

import com.martkans.solvrojakdojade.DTOs.StopDTO;
import com.martkans.solvrojakdojade.domain.Stop;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StopMapperTest {

    private static final String BUS_STOP = "Bus stop";
    private static final Integer ID = 1;

    private StopDTO stopDTO;
    private StopMapper stopMapper = StopMapper.INSTANCE;

    @Before
    public void setUp() throws Exception {
        stopDTO = new StopDTO();
        stopDTO.setId(ID);
        stopDTO.setStopName(BUS_STOP);
    }

    @Test
    public void stopDtoToStop() {
        Stop stop = stopMapper.stopDtoToStop(stopDTO);

        assertEquals(ID, stop.getId());
        assertEquals(BUS_STOP, stop.getStopName());

    }
}