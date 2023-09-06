package com.edusystem.controllers;

import com.edusystem.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edusystem.services.RoleServiceImpl;
import com.edusystem.dto.RoleDto;

import java.util.List;

/**
 *  Role Controller
 */
@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RoleController extends ExceptionController {
    @Autowired
    RoleServiceImpl _roleService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleDto>>> getAllRoles(){
        ApiResponse<List<RoleDto>> apiResponse = new ApiResponse<>();
        try{
            List<RoleDto> roleDtoList = _roleService.getAllRoles();
            apiResponse.setExecutionStatus(true);
            apiResponse.setResult(roleDtoList);
            return ResponseEntity.ok(apiResponse);
        }catch (Exception error){
            apiResponse.setExecutionStatus(false);
            apiResponse.setMessage(error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }
}
