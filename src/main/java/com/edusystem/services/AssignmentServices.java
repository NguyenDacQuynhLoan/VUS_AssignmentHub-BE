package com.edusystem.services;

import com.edusystem.dto.AssignmentDto;

import java.util.List;

/**
 *  Assignment Services Interface
 */
public interface AssignmentServices {
    public List<AssignmentDto> getAllAssignments();

    /**
     *  Get assignments list by user code
     * @param code User Code
     * @return list of assignments
     */
    public List<AssignmentDto> getAssignmentsByUserCode(String code);


    /**
     *  Create new assignment
     * @param model Assignment DTO
     * @return new Assignment DTO
     */
    public AssignmentDto createAssignment(AssignmentDto model);

    /**
     *  Update Assignment
     * @param model assignment DTO model
     * @return updated assignment DTO
     */
    public AssignmentDto updateAssignment(AssignmentDto model);

    /**
     * Delete assignment
     * @param code Assignment code
     * @return true if assignment is deleted
     */
    public boolean deleteAssignment(String code);

    public List<AssignmentDto> filterAssignments(String keyword);
}
