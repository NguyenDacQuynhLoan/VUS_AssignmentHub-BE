package com.edusystem.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.edusystem.dto.SubjectDto;
import com.edusystem.services.SubjectServiceImpl;

/**
 * Subject Controller
 */
@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    @Autowired
    private SubjectServiceImpl subjectService;

    /**
     * Get all subjects
     * @return list of subjects
     */
    @GetMapping
    public List<SubjectDto> getAllSubject(){
        return subjectService.getAllSubject();
    }

    /**
     *  Create new subject
     * @param userCode User code
     * @param model Subject DTO model
     * @return new subject and subject in user
     */
    @PostMapping("/{userCode}/users")
    public SubjectDto createSubject(@PathVariable("userCode") String userCode, @RequestBody SubjectDto model){
        return subjectService.createSubject(userCode,model);
    }

    /**
     *  Update subject
     * @param userCode User code
     * @param model Subject DTO model
     * @return updated subject and subject in user
     */
    @PutMapping("/{userCode}/users")
    public SubjectDto updateSubject(@PathVariable("userCode") String userCode, @RequestBody SubjectDto model){
        return subjectService.updateSubject(userCode, model);
    }

    /**
     *  Delete subject by Code
     * @param code subject code
     * @return true if deleted
     */
    @DeleteMapping("/{code}")
    public boolean deleteSubject(@PathVariable("code") String code){
        return subjectService.deleteSubject(code);
    }
}