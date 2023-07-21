//package com.edusystem.Controllers;
//
//import com.edusystem.Entities.Assignment;
//import com.edusystem.Services.AssignmentServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/assignments")
//public class AssignmentController {
//    @Autowired
//    AssignmentServiceImpl _assignmentService;
//
//    @GetMapping
//    public List<Assignment> getAllAssignment (){
//        return _assignmentService.getAllAssignments();
//    }
//
//    @DeleteMapping("/{id}")
//    public boolean deleteAssignment(@PathVariable("id") Long id){
//        return _assignmentService.deleteAssignment(id);
//    }
//
//    @PutMapping
//    public Assignment udpateAssignment(@RequestBody Assignment model){
//        return  _assignmentService.updateAssignment(model);
//    }
//
