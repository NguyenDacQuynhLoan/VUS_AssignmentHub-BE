package com.edusystem.entities;

import javax.persistence.*;
import java.util.Set;

/**
 *  Role Entity
 */
@Entity(name = "tbl_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_code")
    private String code;

    @Column(name = "role_name")
    private String name;

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

//    public Set<User> getUsers() {
//        return users;
//    }

//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }

    // endregion

    // region constructor
    public Role(Long id, String code, String name, Set<User> users) {
        this.id = id;
        this.code = code;
        this.name = name;
//        this.users = users;
    }
    public Role() {
    }
    // endregion
}