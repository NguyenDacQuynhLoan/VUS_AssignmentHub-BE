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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

/**
 * Entities
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

	@Column(name = "user_gender")
	private String gender;

	@Column(name = "user_birthdate")
	private Date dateOfBirth;

	@Column(name = "user_phone",nullable = true)
	private String phone;

	@Column(name = "user_major",nullable = true)
	private Major major;

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
	@Column(nullable = true)
	private Set<Subject> subjects;

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

	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}
	// endregion

	// region constructor
	public User(Long id, String userCode, String userName, String gender, Date dateOfBirth, String phone, Major major, String email, String password, List<Assignment> assignments, Set<Subject> subjects) {
		this.id = id;
		this.userCode = userCode;
		this.userName = userName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.phone = phone;
		this.major = major;
		this.email = email;
		this.password = password;
		this.assignments = new ArrayList<>();
		this.subjects = subjects;
	}

	public User() {}
	// endregion

	public void AddAssignment(Assignment item){
		assignments.add(item);
		item.setUser(this);
	}

	public void removeAssignment(Assignment item){
		assignments.remove(item);
		item.setUser(null);
	}
}
