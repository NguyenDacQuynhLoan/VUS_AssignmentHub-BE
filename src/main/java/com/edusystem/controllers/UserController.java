package com.edusystem.controllers;

import com.edusystem.services.UserServiceImpl;
import com.edusystem.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController{
	@Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping
    public List<UserDto> findAllUsers() {
        return userServiceImpl.getAllUsers();
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto model){
        return userServiceImpl.createUser(model);
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto model){
        return userServiceImpl.updateUser(model);
    }

    @DeleteMapping("/code/{code}")
    public boolean deleteUser(@PathVariable("code") String code){
        return userServiceImpl.DeleteUser(code);
    }
}
