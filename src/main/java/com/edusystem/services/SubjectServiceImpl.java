package com.edusystem.services;

import com.edusystem.dto.SubjectDto;
import com.edusystem.entities.Subject;
import com.edusystem.repositories.SubjectRepository;
import com.edusystem.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SubjectServiceImpl {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<SubjectDto> getAllSubject(){
        List<SubjectDto> subjectDtoList = new ArrayList<>();
        subjectRepository.findAll().forEach(e ->
                subjectDtoList.add(modelMapper.map(e,SubjectDto.class))
        );
        return subjectDtoList;
    }

    public SubjectDto createSubject (SubjectDto model){
        Subject subjectByCode = subjectRepository.findByCode(model.getCode());
        if(subjectByCode == null){
            // add new subject
            Subject subjectMapped = modelMapper.map(model,Subject.class);
            subjectRepository.save(subjectMapped);

            // add user assigned subject

            return model;
        }
        return null;
    }

    public SubjectDto updateSubject (SubjectDto model){
        Subject subjectByCode = subjectRepository.findByCode(model.getCode());
        if(subjectByCode != null){
            subjectRepository.save(subjectByCode);
            return model;
        }
        return null;
    }

    public boolean deleteSubject(String code){
        Subject subjectByCode = subjectRepository.findByCode(code);
        if( subjectByCode != null){
            subjectRepository.deleteById(subjectByCode.getId());
            return true;
        }
        return false;
    }
}
