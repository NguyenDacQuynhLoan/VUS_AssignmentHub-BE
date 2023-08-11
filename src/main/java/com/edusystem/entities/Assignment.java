package com.edusystem.entities;

import com.edusystem.enums.Grade;
import com.edusystem.enums.Status;

import javax.persistence.*;
import java.util.Date;

/**
 *  Assignment Entity
 */
@Entity(name = "tbl_assignment")
public class Assignment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "assignment_id")
	private Long id;

	@Column(name = "assignment_code")
	private String code;

	@Column(name = "assignment_title")
	private String title;

	private Status status;

	private Grade grade;

	@Column(name = "attachment_file")
	private String file;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	// region Getter & Setter
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	// endregion

	// region Constructor
	public Assignment() {
	}

	public Assignment(Long id, String code, String title, Status status, Grade grade, String file, Date createdDate, Date updatedDate, User user) {
		this.id = id;
		this.code = code;
		this.title = title;
		this.status = status;
		this.grade = grade;
		this.file = file;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.user = user;
	}
	// endregion
}