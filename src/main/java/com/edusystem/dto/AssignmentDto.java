package com.edusystem.dto;

import com.edusystem.enums.Grade;
import com.edusystem.enums.Major;
import com.edusystem.enums.Status;
import lombok.Data;

import java.util.Date;

/**
 * Assignment DTO model
 */
@Data
public class AssignmentDto {
    private Long id;

    private String userCode;

    private String userName;

    private Major major;

    private String subjectName;

    private String code;

    private String title;

    private Status status;

    private Grade grade;

    private String file;

    private Date createdDate;

    private Date updatedDate;
}