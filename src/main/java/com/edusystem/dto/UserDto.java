package com.edusystem.dto;

import java.util.Date;
import java.util.List;

import com.edusystem.entities.Role;
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

    public String userRoleCode;

    public String userRoleName;

    public Date dateOfBirth;

    public String location;

    public String phone;

    public Major major;

    public String email;

    public String password;

    public List<SubjectDto> subjects;

    public List<AssignmentDto> assignments;

//    public UserDto(String userCode, String userName, String gender, String userRoleCode, String userRoleName, Date dateOfBirth, String location, String phone, Major major, String email, String password, List<SubjectDto> subjects, List<AssignmentDto> assignments) {
//        this.userCode = userCode;
//        this.userName = userName;
//        this.gender = gender;
//        this.userRoleCode = userRoleCode;
//        this.userRoleName = userRoleName;
//        this.dateOfBirth = dateOfBirth;
//        this.location = location;
//        this.phone = phone;
//        this.major = major;
//        this.email = email;
//        this.password = password;
//        this.subjects = subjects;
//        this.assignments = assignments;
//    }
}