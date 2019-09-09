package com.martkans.solvrojakdojade.mappers;

import com.martkans.solvrojakdojade.DTOs.LinkDTO;
import com.martkans.solvrojakdojade.domain.Link;
import com.martkans.solvrojakdojade.domain.Stop;
import com.martkans.solvrojakdojade.repositories.StopRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class LinkMapperTest {

    @Mock
    private StopRepository stopRepository;
    private LinkMapper linkMapper;

    private Stop source;
    private Stop target;
    private LinkDTO linkDTO;

    private static final Integer SOURCE_ID = 1;
    private static final Integer TARGET_ID = 2;
    private static final Integer DISTANCE = 30;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        linkMapper = new LinkMapper(stopRepository);

        source = new Stop();
        source.setId(SOURCE_ID);

        target = new Stop();
        target.setId(TARGET_ID);

        linkDTO = new LinkDTO();
        linkDTO.setDistance(DISTANCE);
        linkDTO.setSource(SOURCE_ID);
        linkDTO.setTarget(TARGET_ID);
    }

    @Test
    public void linkDtoToLink() {

        when(stopRepository.findById(SOURCE_ID))
                .thenReturn(Optional.of(source));
        when(stopRepository.findById(TARGET_ID))
                .thenReturn(Optional.of(target));

        Link link = linkMapper.linkDtoToLink(linkDTO);

        assertEquals(DISTANCE, link.getDistance());
        assertEquals(SOURCE_ID, link.getSource().getId());
        assertEquals(TARGET_ID, link.getTarget().getId());
    }
}