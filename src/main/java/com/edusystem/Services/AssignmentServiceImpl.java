package com.edusystem.Services;

import com.edusystem.Entities.Assignment;
import com.edusystem.Repositories.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentServiceImpl {
    @Autowired
    AssignmentRepository _assignmentRepository;


    public List<Assignment> getAllAssignments() {
        return _assignmentRepository.findAll();
    }

    public Assignment getAssignmentById(Long id){
        Optional<Assignment> option = _assignmentRepository.findById(id);
        if(option.isEmpty()){
            return null;
        }
        return option.get();
    }

    public Assignment updateAssignment(Assignment model){
        Assignment existedAssignment = getAssignmentById(model.getId());
        if(existedAssignment != null && model.getCode().equals(existedAssignment.getCode()) ){
            return  _assignmentRepository.save(model);
        }
        return null;
    }

    public Assignment createAssignment(Assignment model){
        Assignment existedAssignment = getAssignmentById(model.getId());
        if(existedAssignment == null){
            return  _assignmentRepository.save(model);
        }
        return null;
    }

    public boolean deleteAssignment(Long id){
        Assignment existedAssignment = getAssignmentById(id);
        if(existedAssignment != null){
            _assignmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
