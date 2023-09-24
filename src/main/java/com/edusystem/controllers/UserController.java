package com.edusystem.controllers;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edusystem.services.ExceptionService;
import com.edusystem.services.UserServiceImpl;
import com.edusystem.dto.ApiResponse;
import com.edusystem.dto.ChangePassword;
import com.edusystem.dto.UserAssignmentFilter;
import com.edusystem.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

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
    ){
        ApiResponse<List<UserDto>> ApiResult = new ApiResponse<>();
        try{
            List<UserDto> userDtoList = userServiceImpl.getAllUsersPaging(index,size);
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
     * Get Total Number of Users
     * @return Total number
     */
    @GetMapping("/total")
    public Long countUser (){
        return userServiceImpl.totalUsers();
    }

    /**
     *  Export List of Users
     * @return
     */
    @GetMapping("/export")
    public ResponseEntity<Resource> exportUsers(){
        try {
            byte[] csvBytes = userServiceImpl.exportUsers();

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=userList.csv");
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            ByteArrayResource resource = new ByteArrayResource(csvBytes);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(csvBytes.length)
                    .body(resource);
        }catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/import")
    public List<UserDto> importUsers(@RequestBody MultipartFile file){
        try {
            if(file == null){
                throw new Exception("File is not exist");
            }
            List<UserDto> dtoList =  userServiceImpl.importUsers(file);
            return dtoList;
        }catch (Exception error){
//            return error.getMessage();
            return null;
        }
    }

    /**
     *  Search all users
     * @param index Page Index
     * @param size Page Size
     * @param keyword Search keyword
     * @return Searched all users
     */
    @GetMapping("/{pageIndex}/{pageSize}/search/{keyword}")
    public ResponseEntity<ApiResponse<List<UserDto>>> SearchAllUsers(
        @PathVariable("pageIndex") Integer index,
        @PathVariable("pageSize" ) Integer size,
        @PathVariable("keyword") String keyword
    ){
        ApiResponse<List<UserDto>> ApiResult = new ApiResponse<>();
        try{
            List<UserDto> userDtoList = userServiceImpl.searchUsers(index,size,keyword.toLowerCase());
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
     *  Filter all users
     * @param index Page Index
     * @param size Page Size
     * @param model User Assignment Filter DTO model
     * @return List Filtered Users
     */
    @PostMapping("/{pageIndex}/{pageSize}/filter")
    public ResponseEntity<ApiResponse<List<UserDto>>> filterAllUsers(
            @PathVariable("pageIndex") Integer index,
            @PathVariable("pageSize" ) Integer size,
            @RequestBody UserAssignmentFilter model
    ){
        ApiResponse<List<UserDto>> ApiResult = new ApiResponse<>();
        try{
            List<UserDto> userDtoList = userServiceImpl.filterUsers(index,size,model);
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
            ApiResult.setMessage("User "+ updatedUserDTO.getUserCode() + " is created");
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
            ApiUserDto.setMessage("User " + updatedUserDTO.getUserCode()+ " is updated");
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
    @PutMapping({"/updatePassword"})
    public ResponseEntity<ApiResponse<Boolean>> updateUserPassword(@RequestBody ChangePassword model) {
        ApiResponse<Boolean> ApiResult = new ApiResponse<>();
        try {
            Boolean updatedResult = this.userServiceImpl.updateUserPassword(model);
            ApiResult.setExecutionStatus(true);
            ApiResult.setResult(updatedResult);
            ApiResult.setMessage("Update Password successful");
            return ResponseEntity.ok(ApiResult);
        } catch (Exception var4) {
            ApiResult.setExecutionStatus(false);
            ApiResult.setMessage(var4.getMessage());
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