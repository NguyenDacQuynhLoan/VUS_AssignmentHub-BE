package com.edusystem.Controllers;

import com.edusystem.Entities.Subject;
import com.edusystem.Services.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    @Autowired
    SubjectServiceImpl _subjectService;

    @GetMapping
    public List<Subject> getAllSubject(){
        return _subjectService.getAllSubject();
    }
}
