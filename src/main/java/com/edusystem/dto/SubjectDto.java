package com.edusystem.dto;

import com.edusystem.enums.Major;
import lombok.Data;

import java.util.Set;

@Data
public class SubjectDto {

    public String code;

    public String name;

    public Major major;

//    public Set<UserDto> users;
}
