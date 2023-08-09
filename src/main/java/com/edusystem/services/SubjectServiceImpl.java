package com.edusystem.services;

import com.edusystem.dto.SubjectDto;
import com.edusystem.dto.UserDto;
import com.edusystem.entities.Subject;
import com.edusystem.entities.User;
import com.edusystem.repositories.SubjectRepository;
import com.edusystem.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    public SubjectDto createSubject (String userCode ,SubjectDto model){
        User usertest1 =  userRepository.findBySubjectsCode(model.getCode());
        List<User> usertest2 =  userRepository.findUsersBySubjectsCode(model.getCode());

        Subject subjectByCode = subjectRepository.findByCode(model.getCode());
        if(subjectByCode == null){

            // create new subject
            Subject subjectMapped = modelMapper.map(model,Subject.class);
            subjectRepository.save(subjectMapped);

            // add subject to user
            User user =  userRepository.findByUserCode(userCode);

            user.AddSubject(subjectMapped);

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