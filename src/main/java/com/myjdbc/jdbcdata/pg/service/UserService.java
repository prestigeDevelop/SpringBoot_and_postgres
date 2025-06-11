package com.myjdbc.jdbcdata.pg.service;

import com.myjdbc.jdbcdata.dto.UserDTO;
import com.myjdbc.jdbcdata.dto.UserMapper;
import com.myjdbc.jdbcdata.pg.entity.User;
import com.myjdbc.jdbcdata.pg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserService {
    @Qualifier("jpaUserRepository")
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO getUserDTO(User user) {
        return userMapper.toDTO(user);
    }

    public User getUserEntity(UserDTO userDTO) {
        return userMapper.toEntity(userDTO);
    }

    //@Cacheable(value = "usersCache")
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @Transactional
    //@CacheEvict(value = "usersCache", key = "#userDTO.id")
    //@CachePut(value = "usersCache", key = "#userDTO.id")
    public UserDTO saveUser(UserDTO userDTO) {
        try {
            // Validate password
            if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
                throw new IllegalArgumentException("Password must not be null or empty");
            }
            User toSave = getUserEntity(userDTO);
            String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
            toSave.setPasswordHash(hashedPassword);
            return userMapper.toDTO(userRepository.save(toSave));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid password provided: " + e.getMessage(), e);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("A user with the same username already exists", e);
        } catch (Exception e) {
            // Catch-all for unexpected exceptions
            throw new RuntimeException("An unexpected error occurred while saving the user", e);
        }
    }
}
