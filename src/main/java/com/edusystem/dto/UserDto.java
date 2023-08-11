package com.edusystem.dto;

import java.util.Date;
import java.util.List;
import lombok.Data;

import com.edusystem.enums.Major;

/**
 *  User DTO model
 */
@Data
public class UserDto {
    public String userCode;

    public String userName;

    public String gender;

    public Date dateOfBirth;

    public String phone;

    public Major major;

    public String email;

    public String password;

    public List<SubjectDto> subjects;

    public List<AssignmentDto> assignments;
}