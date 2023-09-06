package com.edusystem.controllers;

import com.edusystem.dto.ApiResponse;
import com.edusystem.dto.ChangePassword;
import com.edusystem.entities.User;
import com.edusystem.repositories.UserRepository;
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
     *  Get All User
     * @return User DTO List
     */
    @GetMapping("/{pageIndex}/{pageSize}")
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers(
            @PathVariable("pageIndex") Integer index,
            @PathVariable("pageSize" ) Integer size
    ) {
        ApiResponse<List<UserDto>> ApiResult = new ApiResponse<>();
        try{
            List<UserDto> userDtoList = userServiceImpl.getAllUsers();
            int start = index * size;
            int end = Math.min(start + size, userDtoList.size());
            userDtoList = userDtoList.subList(start,end);

            ApiResult.setExecutionStatus(true);
            ApiResult.setResult(userDtoList);
            return ResponseEntity.ok(ApiResult);
        }catch (Exception error){
            ApiResult.setExecutionStatus(false);
            ApiResult.setMessage(error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResult);
        }
    }

    /**
     * Get User by code
     * @param code User Code
     * @return Detected User DTO
     */
    @GetMapping("/{userCode}")
    public ResponseEntity<ApiResponse<UserDto>> getUserByUserCode(
            @PathVariable("userCode") String code
    ){
        ApiResponse<UserDto> apiResponse = new ApiResponse<>();
        try {
            UserDto userDto = userServiceImpl.getUserByCode(code);
            apiResponse.setExecutionStatus(true);
            apiResponse.setResult(userDto);
            return ResponseEntity.ok(apiResponse);
        }catch (Exception error){
            apiResponse.setExecutionStatus(false);
            apiResponse.setMessage(error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    /**
     * Create New User
     * @param model User DTO model
     * @return New User DTO
     */
    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> createUser(@RequestBody UserDto model){
        ApiResponse<UserDto> ApiResult = new ApiResponse<>();
        try{
            UserDto updatedUserDTO = userServiceImpl.createUser(model);
            ApiResult.setExecutionStatus(true);
            ApiResult.setResult(updatedUserDTO);
            ApiResult.setMessage("User is created");
            return ResponseEntity.ok(ApiResult);
        }catch(ExceptionService error){
            ApiResult.setExecutionStatus(false);
            ApiResult.setMessage(error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResult);
        }
    }

    /**
     * Update User
     * @param model User DTO
     * @return Updated User DTO
     */
    @PutMapping
    public ResponseEntity<ApiResponse<UserDto>> updateUser(@RequestBody UserDto model){
        ApiResponse<UserDto> ApiUserDto = new ApiResponse<>();
        try{
            UserDto updatedUserDTO = userServiceImpl.updateUser(model);
            ApiUserDto.setExecutionStatus(true);
            ApiUserDto.setResult(updatedUserDTO);
            return ResponseEntity.ok(ApiUserDto);
        }catch(Exception error){
            ApiUserDto.setExecutionStatus(false);
            ApiUserDto.setMessage(error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiUserDto);
        }
    }

    /**
     * Update User Password
     * @param model Change Password Model
     * @return True if updated password success
     */
    @PutMapping("/updatePassword")
    public ResponseEntity<ApiResponse<Boolean>> updateUserPassword(@RequestBody ChangePassword model){
        ApiResponse<Boolean> ApiResult = new ApiResponse<>();
        try{
            Boolean updatedResult = userServiceImpl.updateUserPassword(model);
            ApiResult.setExecutionStatus(true);
            ApiResult.setResult(updatedResult);
            return ResponseEntity.ok(ApiResult);
        }catch (Exception error){
            ApiResult.setExecutionStatus(false);
            ApiResult.setMessage(error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResult);
        }
    }

    /**
     * Delete User
     * @param code User Code
     * @return True if user deleted
     */
    @DeleteMapping("/{code}")
    public ResponseEntity<ApiResponse<Boolean>> deleteUser(@PathVariable("code") String code){
        ApiResponse<Boolean> ApiResult = new ApiResponse<>();
        try{
            Boolean deleteResult = userServiceImpl.DeleteUser(code);
            ApiResult.setExecutionStatus(true);
            ApiResult.setResult(deleteResult);
            return  ResponseEntity.ok(ApiResult);
        }catch (Exception error){
            ApiResult.setExecutionStatus(false);
            ApiResult.setMessage(error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResult);
        }
    }
}
