package com.edusystem.dto;

import lombok.Data;

@Data
public class ChangePassword {
    public String userCode;

    public String oldPassword;

    public String newPassword;
}