package com.myjdbc.jdbcdata.controller;

import com.myjdbc.jdbcdata.dto.UserDTO;
import com.myjdbc.jdbcdata.pg.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "Operations pertaining to users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @GetMapping("/findAll")
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOList = userService.getAllUsers();
        return userDTOList;
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

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully updated"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public UserDTO updateUser(@PathVariable Integer id, @RequestBody @Validated UserDTO userDTO) {

        return userService.updateUser(id, userDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public UserDTO getUserById(@PathVariable Integer id) {
        System.out.println("Fetching user with ID: " + id);
        return userService.getUserById(id);
    }
}
