package com.myjdbc.jdbcdata.pg.service;

import com.myjdbc.jdbcdata.dto.UserDTO;
import com.myjdbc.jdbcdata.dto.UserMapper;
import com.myjdbc.jdbcdata.exceptions.UserNotFoundException;
import com.myjdbc.jdbcdata.pg.entity.User;
import com.myjdbc.jdbcdata.pg.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
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
    @Cacheable(value = "User", key = "'all'")
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @Transactional
    //@CacheEvict(value = "usersCache", key = "#userDTO.id")
    //@CachePut(value = "usersCache", key = "#userDTO.id")
    @CacheEvict(value = "User", allEntries = true)
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

    @CacheEvict(value = "User", key = "#id")
    public UserDTO updateUser(Integer id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Update fields
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());

        // If password is provided, hash it and set it
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
            existingUser.setPasswordHash(hashedPassword);
        }

        return userMapper.toDTO(userRepository.save(existingUser));
    }

    @Cacheable(value = "User", key = "#id")
    public UserDTO getUserById(Integer id) {
       // log.info("Hello Fetching user with id: {}", id);
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
