// ==========================================================================================
//
// Copyright © 2023 Edu System
//
// History
// ------------------------------------------------------------------------------------------
// Date         Author
// ------------------------------------------------------------------------------------------
// 2022.06.01   LOAN
// ==========================================================================================
//
package com.edusystem.Entities;

import com.edusystem.Assets.Enum.Major;
import com.edusystem.Assets.Enum.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

/**
 * Entities
 */
@Entity(name = "TBL_USER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_code")
	private String userCode;

	private String email;

	private String password;

	@Column(name = "user_major",nullable = true)
	private Major major;

	@Column(nullable = true)
	private Role roleCode;

	@OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Assignment> assignments = new ArrayList<>();

	@ManyToMany
	@JoinTable(
		name ="user_assignedSubject",
		joinColumns = @JoinColumn(name="user_id"),
		inverseJoinColumns = @JoinColumn(name="subject_id")
	)
	private Set<Subject> userSubject;

	// region getter & setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(Role roleCode) {
		this.roleCode = roleCode;
	}

	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	public Set<Subject> getUserSubject() {
		return userSubject;
	}

	public void setUserSubject(Set<Subject> userSubject) {
		this.userSubject = userSubject;
	}
	// endregion

	// region constructor
	public User(Long id, String userName, String userCode, String password, Role roleCode, List<Assignment> assignments, Set<Subject> userSubject) {
		this.id = id;
		this.userName = userName;
		this.userCode = userCode;
		this.password = password;
		this.roleCode = roleCode;
		this.assignments = assignments;
		this.userSubject = userSubject;
	}

	public User() {}
	// endregion
}
