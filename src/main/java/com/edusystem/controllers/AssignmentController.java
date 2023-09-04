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

    /**
     * Get List of Assignment DTO
     * @return Assignment DTO List
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<AssignmentDto>>> getAllAssignment() {
        ApiResponse<List<AssignmentDto>> apiDto = new ApiResponse<>();
        try{
            List<AssignmentDto> assignmentDtoList = assignmentService.getAllAssignments();
            apiDto.setExecutionStatus(true);
            apiDto.setResult(assignmentDtoList);
            return ResponseEntity.ok(apiDto);
        }catch(Exception error){
            apiDto.setExecutionStatus(false);
            apiDto.setMessage(error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiDto);
        }
    }

    /**
     * Get Assignment by User Code
     * @param userCode User Code
     * @return Detected Assignment
     */
    @GetMapping("/{userCode}/user")
    public ResponseEntity<ApiResponse<UserDto>> getAssignmentByCode(@PathVariable("userCode") String userCode) {
        ApiResponse<UserDto> apiDto = new ApiResponse<>();
        try{
            UserDto userDTO = assignmentService.getUserOfAssignment(userCode);
            apiDto.setExecutionStatus(true);
            apiDto.setResult(userDTO);
            return ResponseEntity.ok(apiDto);
        }catch(Exception error){
            apiDto.setExecutionStatus(false);
            apiDto.setMessage(error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiDto);
        }
    }

    @GetMapping("/filter/{keyword}")
    public ResponseEntity<ApiResponse<List<AssignmentDto>>> filterAssignments(@PathVariable("keyword") String keyword){
        ApiResponse<List<AssignmentDto>> apiDto = new ApiResponse<>();
        try{
            List<AssignmentDto> assignmentDtoList = assignmentService.filterAssignments(keyword);
            apiDto.setExecutionStatus(true);
            apiDto.setResult(assignmentDtoList);
            return ResponseEntity.ok(apiDto);
        }catch(Exception error){
            apiDto.setExecutionStatus(false);
            apiDto.setMessage(error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiDto);
        }
    }

    /**
     *  Create new Assignment
     * @param model Assignment DTO model
     * @return new Assignment DTO
     */
    @PostMapping
    public ResponseEntity<ApiResponse<AssignmentDto>> createAssignment(@RequestBody AssignmentDto model){
        ApiResponse<AssignmentDto> apiDto = new ApiResponse<>();
        try{
            AssignmentDto assignmentDto = assignmentService.createAssignment(model);
            apiDto.setExecutionStatus(true);
            apiDto.setResult(assignmentDto);
            return ResponseEntity.ok(apiDto);
        }catch(Exception error){
            apiDto.setExecutionStatus(false);
            apiDto.setMessage(error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiDto);
        }
    }

    /**
     * Update Assignment
     * @param model Assignment DTO model
     * @return Updated Assignment DTO
     */
    @PutMapping
    public ResponseEntity<ApiResponse<AssignmentDto>> updateAssignment(@RequestBody AssignmentDto model){
        ApiResponse<AssignmentDto> apiResponse = new ApiResponse<>();
        try{
            AssignmentDto assignmentDto = assignmentService.updateAssignment(model);
            apiResponse.setExecutionStatus(true);
            apiResponse.setResult(assignmentDto);
            return ResponseEntity.ok(apiResponse);
        }catch (Exception error){
            apiResponse.setExecutionStatus(false);
            apiResponse.setMessage(error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    /**
     * Delete Assignment
     * @param code Assignment Code
     * @return true if assignment is deleted
     */
    @DeleteMapping("/{code}")
    public ResponseEntity<ApiResponse<Boolean>> deleteAssignment(@PathVariable("code") String code) {
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        try{
            Boolean deletedAssignment = assignmentService.deleteAssignment(code);
            apiResponse.setExecutionStatus(true);
            apiResponse.setResult(deletedAssignment);
            return ResponseEntity.ok(apiResponse);
        }catch(Exception error){
            apiResponse.setExecutionStatus(false);
            apiResponse.setMessage(error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }
}