package com.martkans.solvrojakdojade.restapp.mappers;

import com.martkans.solvrojakdojade.restapp.DTOs.LinkDTO;
import com.martkans.solvrojakdojade.restapp.domain.Link;
import com.martkans.solvrojakdojade.restapp.repositories.StopRepository;
import org.springframework.stereotype.Component;

@Component
public class LinkMapper {

    private final StopRepository stopRepository;

    public LinkMapper(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    public Link linkDtoToLink(LinkDTO linkDTO) {
        Link link = new Link();

        link.setDistance(linkDTO.getDistance());

        link.getStops().add(stopRepository.findById(linkDTO.getSource()).get());
        link.getStops().add(stopRepository.findById(linkDTO.getTarget()).get());

        return link;
    }
}
