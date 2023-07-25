package com.edusystem.Controllers;

import com.edusystem.Entities.Assignment;
import com.edusystem.Services.AssignmentServiceImpl;
import com.edusystem.dto.AssignmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@CrossOrigin(origins = "*")
public class AssignmentController {
    @Autowired
    AssignmentServiceImpl _assignmentService;

    @GetMapping
    public List<AssignmentDto> getAllAssignment() {
        return _assignmentService.getAllAssignments();
    }
    @GetMapping("/code/{code}")
    public List<AssignmentDto> getAllAssignment(@PathVariable String code) {
        return _assignmentService.getAssignmentsByCode(code);
    }

//    @DeleteMapping("/{id}")
//    public boolean deleteAssignment(@PathVariable("id") Long id) {
//        return _assignmentService.deleteAssignment(id);
//    }
//
//    @PutMapping
//    public Assignment updateAssignment(@RequestBody Assignment model) {
//        return _assignmentService.updateAssignment(model);
//    }
}