package com.martkans.solvrojakdojade.restapp.bootstrap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.martkans.solvrojakdojade.authentication.domain.Role;
import com.martkans.solvrojakdojade.authentication.domain.User;
import com.martkans.solvrojakdojade.authentication.repositories.RoleRepository;
import com.martkans.solvrojakdojade.authentication.services.UserService;
import com.martkans.solvrojakdojade.restapp.DTOs.GraphDTO;
import com.martkans.solvrojakdojade.restapp.mappers.LinkMapper;
import com.martkans.solvrojakdojade.restapp.mappers.StopMapper;
import com.martkans.solvrojakdojade.restapp.repositories.LinkRepository;
import com.martkans.solvrojakdojade.restapp.repositories.StopRepository;
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
    private final RoleRepository roleRepository;

    private final StopMapper stopMapper;
    private final LinkMapper linkMapper;

    private final UserService userService;

    public DataLoader(LinkRepository linkRepository, StopRepository stopRepository, RoleRepository roleRepository, StopMapper stopMapper, LinkMapper linkMapper, UserService userService) {
        this.linkRepository = linkRepository;
        this.stopRepository = stopRepository;
        this.userService = userService;
        this.roleRepository = roleRepository;
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

            log.debug("Stops saved!");

            graphDTO.getLinks()
                    .stream()
                    .map(linkMapper::linkDtoToLink)
                    .forEach(linkRepository::save);

            log.debug("Links saved!");

        } catch (IOException e){
            log.debug("Unable to load graph: " + e.getMessage());
        }

        User user = new User();
        user.setEmail("em");
        user.setUsername("us");
        user.setPassword("pass");

        Role role = new Role();
        role.setName("ADMIN");
        user.addRole(role);

        roleRepository.save(role);
        userService.save(user);

        log.debug("User loaded!");
    }
}
