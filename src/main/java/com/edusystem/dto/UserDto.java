package com.edusystem.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.edusystem.entities.Assignment;
import com.edusystem.entities.Subject;
import com.edusystem.enums.Major;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

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

    public Set<SubjectDto> subjects;

    public List<AssignmentDto> assignments;
}
