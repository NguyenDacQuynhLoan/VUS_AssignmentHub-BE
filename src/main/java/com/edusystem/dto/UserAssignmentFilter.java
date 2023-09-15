package com.edusystem.dto;

import com.edusystem.enums.Grade;
import com.edusystem.enums.Major;

import java.util.Date;

public class UserAssignmentFilter {
    public Major major;

    public String userCode;

    public String userName;

    public String gender;

    public String subject;

    public String assignmentTitle;

    public Boolean isChecked;

    public Grade grade;

    public Date startDate;

    public Date endDate;
}