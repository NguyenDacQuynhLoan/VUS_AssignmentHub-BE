package com.edusystem.dto;

import lombok.Data;

@Data
public class ChangePassword {
    public String userCode;

    public String currentPassword;

    public String newPassword;
}