package com.myjdbc.jdbcdata.mappers;

import com.myjdbc.jdbcdata.dto.UserDTO;
import com.myjdbc.jdbcdata.pg.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
        // Ignore password in auto-mapping
    void updateUserFromDto(UserDTO userDTO, @MappingTarget User user);
}
