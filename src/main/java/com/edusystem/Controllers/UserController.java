package com.edusystem.Controllers;

import com.edusystem.Entities.User;
import com.edusystem.Services.UserServiceImpl;
//import com.edusystem.Services.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController{

	@Autowired
    UserServiceImpl _userServiceImpl;

    @PostMapping
    public User createUser(@RequestBody User model){
        return _userServiceImpl.createAsync(model);
    }
//
//    public User updateUser(@RequestBody User model){
//        return _userServiceImpl.updateAsync(model,model.getId());
//    }
//    @GetMapping
//    public List<User> findAllUsers() {
//        return _userServiceImpl.getAllAsync();
//    }
//
//    @GetMapping("/{code}")
//    public User findbyUser(@RequestBody Long id ,@PathParam("code") String code ){
//        if(id <= 0){
//            return null;
//        }
//        return _userServiceImpl.getByIdAsync(id);
//    }
//
//    @DeleteMapping
//    public void deleteUser(@RequestBody Long id){
//        _userServiceImpl.DeleteItemAsync(id);
//    }
}
