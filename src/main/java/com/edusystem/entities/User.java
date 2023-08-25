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
package com.edusystem.entities;

import com.edusystem.enums.Major;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * User Entity
 */
@Entity(name = "tbl_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "user_code")
	private String userCode;

	@Column(name = "user_name")
	private String userName;

//	@Column(name = "user_role")
//	private Role userRole;

	@Column(name = "user_major")
	private Major major;

	@Column(name = "user_gender")
	private String gender;

	@Column(name = "user_birthdate",nullable = true)
	private Date dateOfBirth;

	@Column(name = "user_location",nullable = true)
	private String location;

	@Column(name = "user_phone",nullable = true)
	private String phone;

	private String email;

	private String password;

	@OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Assignment> assignments = new ArrayList<>();

	@ManyToMany
	@JoinTable(
			name = "user_subject",
			joinColumns = @JoinColumn(name ="user_id"),
			inverseJoinColumns = @JoinColumn(name = "subject_id")
	)
	private List<Subject> subjects = new ArrayList<>();

	// region getter & setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public List<Assignment> getAssignments() {
		return assignments;
	}
	// endregion

	// region constructor
	public User(Long id, String userCode, String userName, Major major, String gender, Date dateOfBirth, String location, String phone, String email, String password, List<Assignment> assignments, List<Subject> subjects) {
		this.id = id;
		this.userCode = userCode;
		this.userName = userName;
		this.major = major;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.location = location;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.assignments = assignments;
		this.subjects = subjects;
	}

	public User() {}
	// endregion

	/**
	 *  Add Assignment entity to User entity
	 * @param assignment Assignment entity
	 */
	public void addAssignment(Assignment assignment){
		this.assignments.add(assignment);
		assignment.setUser(this);
	}

	/**
	 *  Remove assignment entity in detect User entity
	 * @param assignment Assignment entity
	 */
	public void removeAssignment(Assignment assignment){
		this.assignments.remove(assignment);
		assignment.setUser(null);
	}

	/**
	 *  Add Subject entity to User entity
	 * @param subject Subject entity
	 */
	public void addSubject(Subject subject){
		this.subjects.add(subject);
		subject.getUsers().add(this);
	}

	/**
	 *  Remove Subject entity in User entity
	 * @param subject Subject entity
	 */
	public void removeSubject(Subject subject){
		this.subjects.remove(subject);
		subject.getUsers().remove(this);
	}
}