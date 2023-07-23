package com.edusystem.Services;

import com.edusystem.Entities.Subject;
import com.edusystem.Repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl {
    @Autowired
    SubjectRepository _subjectRepository;

    public List<Subject> getAllSubject(){
        return _subjectRepository.findAll();
    }

    public Subject getSubjectById(Long id){
        Optional<Subject> result = _subjectRepository.findById(id);
        if (result.isEmpty()){
            return null;
        }
        return result.get();
    }
}
