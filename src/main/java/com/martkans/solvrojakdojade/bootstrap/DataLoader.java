package com.martkans.solvrojakdojade.bootstrap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.martkans.solvrojakdojade.DTOs.GraphDTO;
import com.martkans.solvrojakdojade.mappers.LinkMapper;
import com.martkans.solvrojakdojade.mappers.StopMapper;
import com.martkans.solvrojakdojade.repositories.LinkRepository;
import com.martkans.solvrojakdojade.repositories.StopRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

    private static final String JSON_FILE_LOCATION = "/solvro-files/solvro_city.json";

    private final LinkRepository linkRepository;
    private final StopRepository stopRepository;

    private final StopMapper stopMapper;
    private final LinkMapper linkMapper;

    public DataLoader(LinkRepository linkRepository, StopRepository stopRepository, StopMapper stopMapper, LinkMapper linkMapper) {
        this.linkRepository = linkRepository;
        this.stopRepository = stopRepository;
        this.stopMapper = stopMapper;
        this.linkMapper = linkMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<GraphDTO> typeReference = new TypeReference<GraphDTO>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream(JSON_FILE_LOCATION);

        try {
            GraphDTO graphDTO = mapper.readValue(inputStream, typeReference);

            log.debug("Graph loaded!");

            graphDTO.getNodes()
                    .stream()
                    .map(stopMapper::stopDtoToStop)
                    .forEach(stopRepository::save);

            graphDTO.getLinks()
                    .stream()
                    .map(linkMapper::linkDtoToLink)
                    .forEach(linkRepository::save);

        } catch (IOException e){
            log.debug("Unable to load graph: " + e.getMessage());
        }
    }
}
