package com.myjdbc.jdbcdata.pg.service;

import com.myjdbc.jdbcdata.dto.UserDTO;
import com.myjdbc.jdbcdata.dto.UserMapper;
import com.myjdbc.jdbcdata.pg.entity.User;
import com.myjdbc.jdbcdata.pg.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO getUserDTO(User user) {
        return userMapper.toDTO(user);
    }

    public User getUserEntity(UserDTO userDTO) {
        return userMapper.toEntity(userDTO);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .toList();
    }

}
