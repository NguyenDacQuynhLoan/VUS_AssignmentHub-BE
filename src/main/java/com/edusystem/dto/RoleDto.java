package com.edusystem.dto;

import lombok.Data;

import java.util.List;

/**
 * Role DTO model
 */
@Data
public class RoleDto {

    public String code;

    public String name;

    public String userCode;

    public List<UserDto> users;
}
