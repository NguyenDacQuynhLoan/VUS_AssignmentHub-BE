package com.edusystem.Entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class UserEntity {

	//properties
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	
	private String password;
	
	private LocalDate assignedDate;

	@OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Assignment> assignments = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(LocalDate assignedDate) {
		this.assignedDate = assignedDate;
	}

	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	public UserEntity(Long id, String username, String password, LocalDate assignedDate, List<Assignment> assignments) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.assignedDate = assignedDate;
		this.assignments = assignments;
	}

	public UserEntity() {
		super();
	}
}
