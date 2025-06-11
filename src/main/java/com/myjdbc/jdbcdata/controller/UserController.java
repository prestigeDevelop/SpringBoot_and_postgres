package com.myjdbc.jdbcdata.controller;

import com.myjdbc.jdbcdata.dto.UserDTO;
import com.myjdbc.jdbcdata.pg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findAll")
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOList = userService.getAllUsers();
        return userDTOList;
    }

    @PostMapping("/save")
    public UserDTO saveUser(@RequestBody @Validated UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }
}
