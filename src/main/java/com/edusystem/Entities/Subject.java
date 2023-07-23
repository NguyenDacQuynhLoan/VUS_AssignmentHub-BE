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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
    // endregion

    // region constructor
    public Subject() {
    }

    public Subject(Long id, String code, String name, Set<User> users) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.users = users;
    }
    //endregion
}
