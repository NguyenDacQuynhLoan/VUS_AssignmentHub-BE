package com.edusystem.services;

import com.edusystem.entities.Assignment;
import com.edusystem.repositories.AssignmentRepository;
import com.edusystem.dto.AssignmentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *  Assignment Services
 */
@Service
public class AssignmentServiceImpl implements  AssignmentServices{
    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Get all assignment item
     * @return assignment list
     */
    @Override
    public List<AssignmentDto> getAllAssignments() {
        List<AssignmentDto> listAssignmentDto  = new ArrayList<>();
        assignmentRepository.findAll()
                .stream()
                .forEach(e ->
                listAssignmentDto.add(modelMapper.map(e,AssignmentDto.class)));
        return  listAssignmentDto;
    }

    /**
     *  Get assignments list by user code
     * @param code User Code
     * @return list of assignments
     */
    @Override
    public List<AssignmentDto> getAssignmentsByUserCode(String code){
        List<AssignmentDto> listAssignmentDto = new ArrayList<>();
        assignmentRepository.findByUserUserCode(code)
                .stream()
                .forEach(e ->
                    listAssignmentDto.add(modelMapper.map(e,AssignmentDto.class)));;
        return listAssignmentDto;
    }

    /**
     * Get assignment by Id
     * @param id Assignment Id
     * @return assignment if Id is found
     */
    private Assignment getAssignmentById(Long id){
        Optional<Assignment> option = assignmentRepository.findById(id);
        if(option.isEmpty()){
            return null;
        }
        return option.get();
    }

    /**
     *  Create new assignment
     * @param model Assignment DTO
     * @return new Assignment DTO
     */
    @Override
    public AssignmentDto createAssignment(AssignmentDto model){
        if (assignmentRepository.findByCode(model.getCode()) == null
                &&  getAssignmentById(model.getId())                 == null){
            // convert model to entity
            Assignment assignment = modelMapper.map(model,Assignment.class);
            assignmentRepository.save(assignment);
            return model;
        }
        return null;
    }

    /**
     *  Update Assignment
     * @param model assignment DTO model
     * @return updated assignment DTO
     */
    @Override
    public AssignmentDto updateAssignment(AssignmentDto model){
        if (assignmentRepository.findByCode(model.getCode()) != null
        &&  getAssignmentById(model.getId())                 != null){
            // convert model to entity
            Assignment assignment = modelMapper.map(model,Assignment.class);
            assignmentRepository.save(assignment);
            return model;
        }
        return null;
    }

    /**
     * Delete assignment
     * @param code Assignment code
     * @return true if assignment is deleted
     */
    @Override
    public boolean deleteAssignment(String code){
        Assignment assignmentByCode = assignmentRepository.findByCode(code);
        if(assignmentByCode == null){
            return false;
        }

        Assignment existedAssignment = getAssignmentById(assignmentByCode.getId());
        if(existedAssignment != null){
            assignmentRepository.deleteById(assignmentByCode.getId());
            return true;
        }
        return false;
    }

    /**
     * Filter Assignment
     * @param keyword keyword for user code or assignment code or assignment tile
     * @return filtered assignment
     */
    @Override
    public List<AssignmentDto> filterAssignments(String keyword) {
        // filter by user code
        List<AssignmentDto> filteredList = getAssignmentsByUserCode(keyword);
        // filter by title & code
        assignmentRepository.filterAssignments(keyword).forEach(e ->
                filteredList.add(modelMapper.map(e,AssignmentDto.class)));
        return filteredList;
    }
}