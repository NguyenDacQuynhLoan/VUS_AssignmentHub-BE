package com.edusystem.services;

import com.edusystem.dto.UserDto;
import com.edusystem.entities.Assignment;
import com.edusystem.entities.User;
import com.edusystem.repositories.AssignmentRepository;
import com.edusystem.dto.AssignmentDto;
import com.edusystem.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *  Assignment Services
 */
@Service
public class AssignmentServiceImpl{
    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Get all assignment item
     * @return assignment list
     */
    public List<AssignmentDto> getAllAssignments() {
        try {
            return assignmentRepository.findAll()
                    .stream()
                    .map(e ->{
                        AssignmentDto dto = modelMapper.map(e,AssignmentDto.class);
                        dto.setUserCode(e.getUser().getUserCode());
                        return  dto;
                    }).collect(Collectors.toList());
        }catch (Exception error) {
            throw new ExceptionService(error.getMessage());
        }
    }

    /**
     * Get Owner User of Assignment
     * @param userCode User Code
     * @return User information
     */
    public UserDto getUserOfAssignment(String userCode){
        try{
            User user = userRepository.findByUserCode(userCode);
            if(user == null){
                throw new ExceptionService("Can\'t find User");
            }

            UserDto dto = modelMapper.map(user,UserDto.class);
            dto.setAssignments(null);
            return dto;
        }catch (Exception error){
            throw new ExceptionService(error.getMessage());
        }
    }

    /**
     *  Get assignments list by user code
     * @param code User Code
     * @return list of assignments
     */
    public List<AssignmentDto> getAssignmentsByUserCode(String code){
        try{
            List<AssignmentDto> listAssignmentDto = new ArrayList<>();
            listAssignmentDto = assignmentRepository.findByUserUserCode(code)
                    .stream()
                    .map(e ->{
                        AssignmentDto dto = modelMapper.map(e,AssignmentDto.class);
                        dto.setUserCode(code);
                        return  dto;
                    }).collect(Collectors.toList());
            return  listAssignmentDto;
        }catch (Exception error){
            throw new ExceptionService(error.getMessage());
        }
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
    public AssignmentDto createAssignment(AssignmentDto model){
        try{
            if(assignmentRepository.findByCode(model.getCode()) != null){
                throw new ExceptionService("Assignment Code is existed");
            }

            if(getAssignmentById(model.getId()) == null){
                throw new ExceptionService("Assignment is existed");
            }

            // convert model to entity
            Assignment assignment = modelMapper.map(model,Assignment.class);
            assignmentRepository.save(assignment);

            // add to User by User code
            User user = userRepository.findByUserCode(model.getUserCode());
            user.addAssignment(assignment);

            return model;
        }catch(ExceptionService error){
            throw new ExceptionService(error.getMessage());
        }
    }

    /**
     *  Update Assignment
     * @param model assignment DTO model
     * @return updated assignment DTO
     */
    public AssignmentDto updateAssignment(AssignmentDto model){
        try{
            Assignment instanceAssignment = assignmentRepository.findByCode(model.getCode());
            if(getAssignmentById(model.getId()) == null){

            }

            if(instanceAssignment == null){

            }

            // convert model to entity
            Assignment assignment = modelMapper.map(model,Assignment.class);
            assignmentRepository.save(assignment);

            // find user had this assignment
            User user = userRepository.findByUserCode(model.getUserCode());

            // remove old assignment
            user.removeAssignment(instanceAssignment);

            // add new assignment
            user.addAssignment(assignment);
            return model;

        }catch (Exception error){
            throw new ExceptionService(error.getMessage());
        }
    }

    /**
     * Delete assignment
     * @param code Assignment code
     * @return true if assignment is deleted
     */
    public boolean deleteAssignment(String code){
        try{
            Assignment assignmentByCode = assignmentRepository.findByCode(code);
            if(assignmentByCode == null){
                return false;
            }

            Assignment existedAssignment = getAssignmentById(assignmentByCode.getId());
            if(existedAssignment != null){
                assignmentRepository.deleteById(assignmentByCode.getId());

                // find deleted assignment in user
                String userCode = modelMapper.map(assignmentByCode,AssignmentDto.class).getUserCode();
                User user = userRepository.findByUserCode(userCode);

                // remove assignment in user
                user.removeAssignment(existedAssignment);
                return true;
            }
            return false;
        }catch (Exception error){
            throw new ExceptionService(error.getMessage());
        }
    }

    /**
     * Filter Assignment
     * @param keyword keyword for user code or assignment code or assignment tile
     * @return filtered assignment
     */
    public List<AssignmentDto> filterAssignments(String keyword) {
        try{
            // filter by user code
            List<AssignmentDto> filteredList = getAssignmentsByUserCode(keyword);

            // filter by title & code
            assignmentRepository.filterAssignments(keyword).forEach(e ->
                    filteredList.add(modelMapper.map(e,AssignmentDto.class)));
            return filteredList;
        }catch(Exception error){
            throw new ExceptionService(error.getMessage());
        }
    }
}