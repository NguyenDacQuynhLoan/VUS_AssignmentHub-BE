package com.edusystem.Entities;

import javax.persistence.*;

@Entity
public class Assignment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	
	private Boolean status;

	private String feedback;
	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	private User user;

	// region getter & setter
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	// endregion

	// region constructor
	public Assignment() {
	}

	public Assignment(String title, Boolean status, String feedback, User user) {
		this.title = title;
		this.status = status;
		this.feedback = feedback;
		this.user = user;
	}
	// endregion
}
