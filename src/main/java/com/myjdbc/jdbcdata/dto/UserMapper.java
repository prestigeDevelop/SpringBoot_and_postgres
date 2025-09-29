package com.myjdbc.jdbcdata.dto;

import com.myjdbc.jdbcdata.pg.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    UserDTO toDTO(User user);

    @Mapping(source = "password", target = "passwordHash")
    User toEntity(UserDTO userDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
        // Ignore password in auto-mapping
    void updateUserFromDto(UserDTO userDTO, @MappingTarget User user);

    void updateUserFromUpdateDto(UserUpdateDTO userUpdateDTO, @MappingTarget User user);
}
