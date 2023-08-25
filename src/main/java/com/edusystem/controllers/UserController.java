package com.edusystem.controllers;

import com.edusystem.dto.ApiResponse;
import com.edusystem.dto.ChangePassword;
import com.edusystem.services.ExceptionService;
import com.edusystem.services.UserServiceImpl;
import com.edusystem.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User Controller
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController extends ExceptionController{
	@Autowired
    private UserServiceImpl userServiceImpl;

    /**
     *  Get All User DTO
     * @return User DTO List
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {
        ApiResponse<List<UserDto>> ApiResult = new ApiResponse<>();
        try{
            List<UserDto> userDtoList = userServiceImpl.getAllUsers();
            ApiResult.setSuccess(true);
            ApiResult.setResult(userDtoList);
            return ResponseEntity.ok(ApiResult);
        }catch (Exception error){
            ApiResult.setSuccess(false);
            ApiResult.setMessage(error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResult);
        }
    }

//    @GetMapping("/email/{userEmail}")
//    public UserDto getUserByEmail(@PathVariable("userEmail") String email) {
//        return userServiceImpl.getUserByEmail(email);
//    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> createUser(@RequestBody UserDto model){
        ApiResponse<UserDto> ApiResult = new ApiResponse<>();
        try{
            UserDto updatedUserDTO = userServiceImpl.createUser(model);
            ApiResult.setSuccess(true);
            ApiResult.setResult(updatedUserDTO);
            return ResponseEntity.ok(ApiResult);
        }catch(ExceptionService error){
            ApiResult.setSuccess(false);
            ApiResult.setMessage(error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResult);
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponse<UserDto>> updateUser(@RequestBody UserDto model){
        ApiResponse<UserDto> ApiUserDto = new ApiResponse<>();
        try{
            UserDto updatedUserDTO = userServiceImpl.updateUser(model);
            ApiUserDto.setSuccess(true);
            ApiUserDto.setResult(updatedUserDTO);
            return ResponseEntity.ok(ApiUserDto);
        }catch(Exception error){
            ApiUserDto.setSuccess(false);
            ApiUserDto.setMessage(error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiUserDto);
        }
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<ApiResponse<Boolean>> updateUserPassword(@RequestBody ChangePassword model){
        ApiResponse<Boolean> ApiResult = new ApiResponse<>();
        try{
            Boolean updatedResult = userServiceImpl.updateUserPassword(model);
            ApiResult.setSuccess(true);
            ApiResult.setResult(updatedResult);
            return ResponseEntity.ok(ApiResult);
        }catch (Exception error){
            ApiResult.setSuccess(false);
            ApiResult.setMessage(error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResult);
        }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<ApiResponse<Boolean>> deleteUser(@PathVariable("code") String code){
        ApiResponse<Boolean> ApiResult = new ApiResponse<>();
        try{
            Boolean deleteResult = userServiceImpl.DeleteUser(code);
            ApiResult.setSuccess(true);
            ApiResult.setResult(deleteResult);
            return  ResponseEntity.ok(ApiResult);
        }catch (Exception error){
            ApiResult.setSuccess(false);
            ApiResult.setMessage(error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResult);
        }
    }
}
