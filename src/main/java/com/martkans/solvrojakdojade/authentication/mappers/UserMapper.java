package com.martkans.solvrojakdojade.authentication.mappers;

import com.martkans.solvrojakdojade.authentication.DTOs.UserDTO;
import com.martkans.solvrojakdojade.authentication.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    User userDtoTOUser(UserDTO userDto);
}
