package com.edusystem.dto;

import com.edusystem.enums.Major;
import lombok.Data;

/**
 * Subject DTO model
 */
@Data
public class SubjectDto {

    public String code;

    public String name;

    public Major major;
}