package com.edusystem.dto;

import java.util.Date;

import com.edusystem.enums.Major;
import lombok.Data;

@Data
public class UserDto {

    public String code;

    public String name;

    public String gender;

    public Date dateOfBirth;

    public String phone;

    public Major major;

    public String email;

    public String password;

}
