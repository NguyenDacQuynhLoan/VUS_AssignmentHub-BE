package com.edusystem.controllers;

import com.edusystem.dto.ChangePassword;
import com.edusystem.services.UserServiceImpl;
import com.edusystem.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User Controller
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController{
	@Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userServiceImpl.getAllUsers();
    }

    @GetMapping("/email/{userEmail}")
    public UserDto getUserByEmail(@PathVariable("userEmail") String email) {
        return userServiceImpl.getUserByEmail(email);
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto model){
        return userServiceImpl.createUser(model);
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto model){
        return userServiceImpl.updateUser(model);
    }

    @PutMapping("/updatePassword")
    public boolean updateUserPassword(@RequestBody ChangePassword model){
        return userServiceImpl.updateUserPassword(model);
    }

    @DeleteMapping("/{code}")
    public boolean deleteUser(@PathVariable("code") String code){
        return userServiceImpl.DeleteUser(code);
    }
}
