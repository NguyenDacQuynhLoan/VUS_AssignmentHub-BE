package com.edusystem.Entities;

import javax.persistence.*;

/**
 *  User Assigned Subject Entity
 */
@Entity(name = "TBL_SUBJECT")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long id;

    @Column(name = "subject_code")
    private String code;

    @Column(name = "subject_name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public Subject() {
    }
}
