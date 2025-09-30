package com.myjdbc.jdbcdata.controller;

import com.myjdbc.jdbcdata.dto.UserDTO;
import com.myjdbc.jdbcdata.dto.UserMapper;
import com.myjdbc.jdbcdata.dto.UserUpdateDTO;
import com.myjdbc.jdbcdata.exceptions.UserNotFoundException;
import com.myjdbc.jdbcdata.pg.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Management", description = "Operations pertaining to users")
public class UserControllerV1 {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserControllerV1(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @GetMapping("/findAll")
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOS = userService.getAllUsers().stream().map(userMapper::toDTO).toList();
        return userDTOS;
    }

    @PostMapping("/save")
    @Operation(summary = "Save a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })

    //POST /save?name=john
    public UserDTO saveUser(@RequestBody @Validated UserDTO userDTO, @RequestParam String name) {
        return userService.saveUser(userDTO);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update an existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully updated"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public UserDTO updateUser(@PathVariable Integer id, @RequestBody @Validated UserUpdateDTO userDTO) {

        return userService.updateUser(id, userDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        log.info("Fetching user with ID: " + id);
        return userService.getUserById(id)
                .map(userMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
