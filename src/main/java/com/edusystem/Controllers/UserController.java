package com.edusystem.Controllers;

import com.edusystem.Entities.User;
import com.edusystem.Services.UserServiceImpl;
//import com.edusystem.Services.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController{
	@Autowired
    UserServiceImpl _userServiceImpl;

    @GetMapping
    public List<User> findAllUsers() {
        List<User> users = _userServiceImpl.getAllUsers();
        return users;
    }

    @PostMapping
    public User createUser(@RequestBody User model){
         return _userServiceImpl.createUser(model);
    }
}
