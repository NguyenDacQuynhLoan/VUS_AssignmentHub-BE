package com.edusystem.dto;

import com.edusystem.Assets.Enum.Grade;
import com.edusystem.Assets.Enum.Status;
import lombok.Data;

import java.util.Date;

@Data
public class AssignmentDto {
    private Long id;

    private String user_code;

    private String code;

    private String title;

    private Status status;

    private Grade grade;

    private String file;

    private Date createdDate;
}