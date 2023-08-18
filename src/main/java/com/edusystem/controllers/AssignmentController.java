package com.edusystem.controllers;

import com.edusystem.dto.UserDto;
import com.edusystem.services.AssignmentServiceImpl;
import com.edusystem.dto.AssignmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Assignment Controller
 */
@RestController
@RequestMapping("/api/assignments")
@CrossOrigin(origins = "*")
public class AssignmentController {
    @Autowired
    private AssignmentServiceImpl assignmentService;

    @GetMapping
    public List<AssignmentDto> getAllAssignment() {
        return assignmentService.getAllAssignments();
    }
    @GetMapping("/{userCode}/user")
    public UserDto getAssignmentByCode(@PathVariable("userCode") String userCode) {
        return assignmentService.getUserOfAssignment(userCode);
    }

    @GetMapping("/filter/{keyword}")
    public List<AssignmentDto> filterAssignments(@PathVariable("keyword") String keyword){
        return assignmentService.filterAssignments(keyword);
    }

    @PostMapping
    public AssignmentDto createAssignment(@RequestBody AssignmentDto model){
        return assignmentService.createAssignment(model);
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