package com.edusystem.services;

import java.util.List;

import com.edusystem.dto.SubjectDto;

/**
 *  Subject Services interface
 */
public interface SubjectServices {
    public List<SubjectDto> getAllSubject();

    /**
     *  Create new subject
     * @param userCode user Code
     * @param model Subject DTO model
     * @return new Subject and add subject to user
     */
    public SubjectDto createSubject (String userCode ,SubjectDto model);

    /**
     *  Update subject
     * @param userCode User code
     * @param model Subject DTO model
     * @return updated Subject and subject in User
     */
    public SubjectDto updateSubject (String userCode ,SubjectDto model);

    /**
     *  Delete subject
     * @param code Subject code
     * @return true if deleted
     */
    public boolean deleteSubject(String code);
}
