package com.edusystem.Controllers;

import com.edusystem.Entities.UserEntity;
import com.edusystem.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping
@RequestMapping("/api/users")
//@CrossOrigin(origins = "*")
public class UserController{
	@Autowired
    UserService _userService;

//	@GetMapping
//    public ResponseEntity<List<UserEntity>> findAllUsers() {
//        List<UserEntity> users = _userService.getAllUsers();
//        return ResponseEntity.ok(users);
//    }
    @GetMapping
    public List<UserEntity> findAllUsers() {
        List<UserEntity> users = _userService.getAllUsers();
        return users;
    }
}
