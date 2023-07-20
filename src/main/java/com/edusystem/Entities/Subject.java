package com.edusystem.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 *  User Assigned Subject Entity
 */
@Entity(name = "tbl_subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long id;

    @Column(name = "subject_code")
    private String code;

    @Column(name = "subject_name")
    private String name;

    @ManyToMany(mappedBy = "subjects")
    @JsonIgnore
    private Set<User> users;

    public Subject() {
    }
}
