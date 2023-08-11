package com.edusystem.services;

import java.util.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edusystem.dto.SubjectDto;
import com.edusystem.entities.Subject;
import com.edusystem.entities.User;
import com.edusystem.repositories.SubjectRepository;
import com.edusystem.repositories.UserRepository;

/**
 * Subject services
 */
@Service
public class SubjectServiceImpl implements SubjectServices{
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     *  Get all subject
     * @return
     */
    public List<SubjectDto> getAllSubject(){
        List<SubjectDto> subjectDtoList = new ArrayList<>();

        subjectRepository.findAll().forEach(e ->
                subjectDtoList.add(modelMapper.map(e,SubjectDto.class))
        );

        return subjectDtoList;
    }

    /**
     *  Create new subject
     * @param userCode user Code
     * @param model Subject DTO model
     * @return new Subject and add subject to user
     */
    public SubjectDto createSubject (String userCode ,SubjectDto model){
        Subject subjectByCode = subjectRepository.findByCode(model.getCode());

        if(subjectByCode == null){
            // create new subject
            Subject subjectMapped = modelMapper.map(model,Subject.class);
            subjectRepository.save(subjectMapped);

            // add subject in user
            User user =  userRepository.findByUserCode(userCode);
            user.addSubject(subjectMapped);
            userRepository.save(user);

            return model;
        }
        return null;
    }

    /**
     *  Update subject
     * @param userCode User code
     * @param model Subject DTO model
     * @return updated Subject and subject in User
     */
    public SubjectDto updateSubject (String userCode ,SubjectDto model){
        Subject subjectByCode = subjectRepository.findByCode(model.getCode());

        if(subjectByCode != null){
            // update subject
            Subject subjectMapped = modelMapper.map(model,Subject.class);
            subjectRepository.save(subjectMapped);

            // update subject in user
            User user = userRepository.findByUserCode(userCode);
            user.removeSubject(subjectByCode);
            user.addSubject(subjectMapped);
            userRepository.save(user);

            return model;
        }
        return null;
    }

    /**
     *  Delete subject
     * @param code Subject code
     * @return true if deleted
     */
    public boolean deleteSubject(String code){
        Subject subjectByCode = subjectRepository.findByCode(code);
        if( subjectByCode != null){
            subjectRepository.deleteById(subjectByCode.getId());
            return true;
        }
        return false;
    }
}