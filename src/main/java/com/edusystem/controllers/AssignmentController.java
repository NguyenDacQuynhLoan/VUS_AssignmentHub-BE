package com.edusystem.controllers;

import com.edusystem.dto.ApiResponse;
import com.edusystem.dto.UserDto;
import com.edusystem.services.AssignmentServiceImpl;
import com.edusystem.dto.AssignmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Assignment Controller
 */
@RestController
@RequestMapping("/api/assignments")
@CrossOrigin(origins = "*")
public class AssignmentController extends ExceptionController{
    @Autowired
    private AssignmentServiceImpl assignmentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AssignmentDto>>> getAllAssignment() {
        ApiResponse<List<AssignmentDto>> apiDto = new ApiResponse<>();
        try{
            List<AssignmentDto> assignmentDtoList = assignmentService.getAllAssignments();
            apiDto.setSuccess(true);
            apiDto.setResult(assignmentDtoList);
            return ResponseEntity.ok(apiDto);
        }catch(Exception error){
            apiDto.setSuccess(false);
            apiDto.setMessage(error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiDto);
        }
    }

    @GetMapping("/{userCode}/user")
    public ResponseEntity<ApiResponse<UserDto>> getAssignmentByCode(@PathVariable("userCode") String userCode) {
        ApiResponse<UserDto> apiDto = new ApiResponse<>();
        try{
            UserDto userDTO = assignmentService.getUserOfAssignment(userCode);
            apiDto.setSuccess(true);
            apiDto.setResult(userDTO);
            return ResponseEntity.ok(apiDto);
        }catch(Exception error){
            apiDto.setSuccess(false);
            apiDto.setMessage(error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiDto);
        }
    }

    @GetMapping("/filter/{keyword}")
    public ResponseEntity<ApiResponse<List<AssignmentDto>>> filterAssignments(@PathVariable("keyword") String keyword){
        ApiResponse<List<AssignmentDto>> apiDto = new ApiResponse<>();
        try{
            List<AssignmentDto> assignmentDtoList = assignmentService.filterAssignments(keyword);
            apiDto.setSuccess(true);
            apiDto.setResult(assignmentDtoList);
            return ResponseEntity.ok(apiDto);
        }catch(Exception error){
            apiDto.setSuccess(false);
            apiDto.setMessage(error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiDto);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AssignmentDto>> createAssignment(@RequestBody AssignmentDto model){
        ApiResponse<AssignmentDto> apiDto = new ApiResponse<>();
        try{
            AssignmentDto assignmentDto = assignmentService.createAssignment(model);
            apiDto.setSuccess(true);
            apiDto.setResult(assignmentDto);
            return ResponseEntity.ok(apiDto);
        }catch(Exception error){
            apiDto.setSuccess(false);
            apiDto.setMessage(error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiDto);
        }
    }
    @PutMapping
    public AssignmentDto updateAssignment(@RequestBody AssignmentDto model){
        return assignmentService.updateAssignment(model);
    }

    @DeleteMapping("/{code}")
    public boolean deleteAssignment(@PathVariable("code") String code) {
        return assignmentService.deleteAssignment(code);
    }
}