package com.martkans.solvrojakdojade.mappers;

import com.martkans.solvrojakdojade.DTOs.LinkDTO;
import com.martkans.solvrojakdojade.domain.Link;
import com.martkans.solvrojakdojade.repositories.StopRepository;
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

        if(stopRepository.findById(linkDTO.getSource()).isPresent()){
            link.setSource(stopRepository.findById(linkDTO.getSource()).get());
        }

        if(stopRepository.findById(linkDTO.getTarget()).isPresent()){
            link.setTarget(stopRepository.findById(linkDTO.getTarget()).get());
        }

        return link;
    }
}