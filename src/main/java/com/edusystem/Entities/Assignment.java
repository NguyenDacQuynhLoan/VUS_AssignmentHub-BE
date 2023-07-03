package com.edusystem.Entities;

import com.edusystem.Assets.Enum.Grade;
import com.edusystem.Assets.Enum.Status;

import javax.persistence.*;
import java.util.Date;

/**
 *  User Assignment Entity
 */
@Entity(name = "TBL_ASSIGNMENT")
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

	@ManyToOne(optional = false,fetch = FetchType.LAZY)
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	// endregion

	// region Constructor
	public Assignment(Long id, String code, String title, Status status, Grade grade, String file, Date createdDate, User user) {
		this.id = id;
		this.code = code;
		this.title = title;
		this.status = status;
		this.grade = grade;
		this.file = file;
		this.createdDate = createdDate;
		this.user = user;
	}

	public Assignment() {
	}
	// endregion
}
