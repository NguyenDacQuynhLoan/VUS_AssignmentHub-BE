package com.edusystem.entities;

import com.edusystem.enums.Major;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *  Assigned Subject Entity
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

    private Major major;

    @ManyToMany(mappedBy = "subjects")
    private List<User> users = new ArrayList<>();

    // region getter & setter
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

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    // endregion

    // region constructor
    public Subject() {
    }

    public Subject(Long id, String code, String name, Major major, List<User> users) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.major = major;
        this.users = users;
    }
    //endregion
}
