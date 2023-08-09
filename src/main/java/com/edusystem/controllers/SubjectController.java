package com.edusystem.controllers;

import com.edusystem.dto.SubjectDto;
import com.edusystem.entities.Subject;
import com.edusystem.services.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    @Autowired
    private SubjectServiceImpl subjectService;

    @GetMapping
    public List<SubjectDto> getAllSubject(){
        return subjectService.getAllSubject();
    }

    @PostMapping("/{userCode}/users")
    public SubjectDto createSubject(@PathVariable("userCode") String code, @RequestBody SubjectDto model){
        return subjectService.createSubject(code,model);
    }

    @PutMapping
    public SubjectDto updateSubject(@RequestBody SubjectDto model){
        return subjectService.updateSubject(model);
    }

    @DeleteMapping("/code/{code}")
    public  boolean deleteSubject(@PathVariable("code") String code){
        return subjectService.deleteSubject(code);
    }
}
