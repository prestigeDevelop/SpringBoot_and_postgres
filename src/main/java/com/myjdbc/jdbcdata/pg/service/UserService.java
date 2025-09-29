package com.myjdbc.jdbcdata.pg.service;

import com.myjdbc.jdbcdata.dto.UserDTO;
import com.myjdbc.jdbcdata.dto.UserMapper;
import com.myjdbc.jdbcdata.dto.UserUpdateDTO;
import com.myjdbc.jdbcdata.exceptions.UserNotFoundException;
import com.myjdbc.jdbcdata.pg.entity.User;
import com.myjdbc.jdbcdata.pg.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

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
    public List<User> getAllUsers() {
        List<User> list = userRepository.findAll();
        list.stream().forEach(System.out::println);
        return list;
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
    public UserDTO updateUser(Integer id, UserUpdateDTO updateDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        // Update non-null fields using MapStruct
        userMapper.updateUserFromUpdateDto(updateDTO, existingUser);
        log.info("Updating user: {}", existingUser);
        // Handle password separately
        if (updateDTO.getPassword() != null && !updateDTO.getPassword().isEmpty()) {
            String hashedPassword = passwordEncoder.encode(updateDTO.getPassword());
            existingUser.setPasswordHash(hashedPassword);
        }
        return userMapper.toDTO(userRepository.save(existingUser));
    }

    @Cacheable(value = "User", key = "#id")
    @CachePut(value = "User", key = "#id")
    //@CacheEvict(value = "User", key = "'all'")
    public UserDTO getUserById(Integer id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
