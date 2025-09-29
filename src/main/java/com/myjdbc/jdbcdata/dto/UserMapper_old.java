package com.myjdbc.jdbcdata.dto;

import com.myjdbc.jdbcdata.pg.entity.User;

//@Component
public class UserMapper_old {
    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setDateOfBirth(user.getDateOfBirth());
        dto.setAddress(user.getAddress());
        dto.setPassword(user.getPasswordHash());
        return dto;
    }

    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setDateOfBirth(dto.getDateOfBirth());
        user.setAddress(dto.getAddress());
        return user;
    }

    public void updateUserFromDto(UserDTO userDTO, User existingUser) {
        if (userDTO.getUsername() != null) {
            existingUser.setUsername(userDTO.getUsername());
        }
        if (userDTO.getEmail() != null) {
            existingUser.setEmail(userDTO.getEmail());
        }
        if (userDTO.getFirstName() != null) {
            existingUser.setFirstName(userDTO.getFirstName());
        }
        if (userDTO.getLastName() != null) {
            existingUser.setLastName(userDTO.getLastName());
        }
        if (userDTO.getPhoneNumber() != null) {
            existingUser.setPhoneNumber(userDTO.getPhoneNumber());
        }
        if (userDTO.getDateOfBirth() != null) {
            existingUser.setDateOfBirth(userDTO.getDateOfBirth());
        }
        if (userDTO.getAddress() != null) {
            existingUser.setAddress(userDTO.getAddress());
        }
        // Note: Password is intentionally not updated here
    }
}
//@Mapper(componentModel = "spring")
//public interface UserMapper {
//    UserDTO toDTO(User user);
//    User toEntity(UserDTO dto);
//}
