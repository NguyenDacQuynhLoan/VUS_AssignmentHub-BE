package com.edusystem.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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

    @OneToMany(mappedBy="userRole", cascade = CascadeType.ALL, orphanRemoval = true)
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    // endregion

    // region constructor
    public Role(Long id, String code, String name, List<User> users) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.users = users;
    }

    public Role() {
    }
    // endregion

    public void addUser(User user){
        this.users.add(user);
        user.setUserRole(this);
    }

    public void removeUser(User user){
        this.users.remove(user);
        user.setUserRole(null);
    }
}